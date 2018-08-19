/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.tool.crawler4j.service.crawler;

import com.github.peterchenhdu.future.tool.crawler4j.model.HourStatistics;
import com.github.peterchenhdu.future.tool.crawler4j.service.hourse.HourStatisticsService;
import com.github.peterchenhdu.future.util.SpringContextUtils;
import com.github.peterchenhdu.future.util.UuidUtils;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author PiChen
 * @since 2018/8/13 23:32
 */
@Service
@DependsOn("springContextUtils")
@EnableScheduling
public class HourStatisticsCrawlerService extends WebCrawler {
    private static long count = 0;
    private String hrefStartWith = "http://www.howzf.com/";
    /**
     * 临时数据文件夹
     */
    private String crawldataFolder = "crawldata";
    /**
     * 爬取线程数
     */
    private String maxThreadNum = "1";
    //请求建个ms
    private int politenessDelay = 1000;
    //最大爬取深度，-1为无限大
    private int maxDepth = 1;
    //爬取得最大页面数,-1为无限大
    private int maxPagesNum = 1;
    //种子数组
    private String[] seedArr = new String[]{
            "http://www.howzf.com/"
//        "http://news.163.com/",
//        "http://news.163.com/rank/",
//        "http://news.163.com/domestic/",
//        "http://news.163.com/world/",
//        "http://news.163.com/special/"
    };

    private HourStatisticsService hourStatisticsService = SpringContextUtils.getBean("hourStatisticsService",
            HourStatisticsService.class);


    private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        // Ignore the url if it has an extension that matches our defined set of
        // image extensions.
//        if (IMAGE_EXTENSIONS.matcher(href).matches()) {
//            return false;
//        }

        // Only accept the url if it is in the "www.ics.uci.edu" domain and
        // protocol is "http".
        return href.startsWith(hrefStartWith);

    }
    @Override
    public void visit(Page page) {


        String url = page.getWebURL().getURL();

        logger.info("URL: {}", url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

            //使用jsoup解析html网页
            Document doc = Jsoup.parse(htmlParseData.getHtml());

//            Element content = doc.getElementById("endText");
//            if (content == null) {
//                logger.info("invalid source:" + url);
//                return;
//            }
            Elements e1 = doc.getElementsByClass("f24 f_blue");
//            System.out.println(e1.get(0).text());
//            System.out.println(e1.get(1).text());
//            System.out.println(e1.get(2).text());
            Elements e2 = doc.getElementsByClass("f30 line16 f_blue");
//            System.out.println(e2.get(2).ownText());
//            System.out.println(e2.get(3).ownText());
//            doc.getElementById();
//
//            HourStatistics hourStatistics = new HourStatistics();
//
//            newsService.saveNews(news);
            HourStatistics hourStatistics = new HourStatistics();
            hourStatistics.setUuid(UuidUtils.getUuid());
            //hourStatistics.setAreaId();
            hourStatistics.setHouseSum(Long.valueOf(e1.get(0).ownText()));
            hourStatistics.setAgentSum(Long.valueOf(e1.get(1).ownText()));
            hourStatistics.setGoldConsultantSum(Long.valueOf(e1.get(2).ownText()));
            hourStatistics.setTodayListCount(Long.valueOf(e2.get(2).ownText()));
            hourStatistics.setTodaySign(Long.valueOf(e2.get(3).ownText()));
            hourStatistics.setCreateDate(new Date());
            hourStatisticsService.save(hourStatistics);
            logger.info("[保存成功-{}] URL: {} ", ++count, url);
        }
    }

    @Scheduled(cron="0 0 0/1 * * ?")
    public void start() throws Exception {

        /*
         * crawlStorageFolder is a folder where intermediate crawl data is
         * stored.
         */
        String crawlStorageFolder = crawldataFolder;

        /*
         * numberOfCrawlers shows the number of concurrent threads that should
         * be initiated for crawling.
         */
        int numberOfCrawlers = Integer.parseInt(maxThreadNum);

        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Be polite: Make sure that we don't send more than 1 request per
         * second (1000 milliseconds between requests).
         */
        config.setPolitenessDelay(politenessDelay);

        /*
         * You can set the maximum crawl depth here. The default value is -1 for
         * unlimited depth
         */
        config.setMaxDepthOfCrawling(maxDepth);

        /*
         * You can set the maximum number of pages to crawl. The default value
         * is -1 for unlimited number of pages
         */
        config.setMaxPagesToFetch(maxPagesNum);

        /**
         * Do you want crawler4j to crawl also binary data ? example: the
         * contents of pdf, or the metadata of images etc
         */
        config.setIncludeBinaryContentInCrawling(false);

        /*
         * Do you need to set a proxy? If so, you can use:
         * config.setProxyHost("proxyserver.example.com");
         * config.setProxyPort(8080);
         *
         * If your proxy also needs authentication:
         * config.setProxyUsername(username); config.getProxyPassword(password);
         */

        /*
         * This config parameter can be used to set your crawl to be resumable
         * (meaning that you can resume the crawl from a previously
         * interrupted/crashed crawl). Note: if you enable resuming feature and
         * want to start a fresh crawl, you need to delete the contents of
         * rootFolder manually.
         */
        config.setResumableCrawling(false);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
//         robotstxtConfig.setEnabled(false);
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);


        //添加种子Url，爬取的起始url
        for (String seed : seedArr) {
            controller.addSeed(seed);
        }

        //开始爬取，阻塞操作，直到爬取结束.
        controller.start(HourStatisticsCrawlerService.class, numberOfCrawlers);
        logger.info("Finish Crawler...");
    }
}
