/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.job.auto;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.github.peterchenhdu.future.common.constant.CommonConstants;
import com.github.peterchenhdu.future.example.crawler4j.entity.HouseDetail;
import com.github.peterchenhdu.future.example.crawler4j.mapper.HouseDetailMapper;
import com.github.peterchenhdu.future.util.StringUtils;
import com.github.peterchenhdu.future.util.UuidUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author PiChen
 * @since 2018/8/18 19:05
 */
@Component
public class HouseDetailCrawlerJob {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HouseDetailMapper houseDetailMapper;

    @PostConstruct
    public void execute()  {
        this.start();

    }

//    public static void main(String[] args) throws InterruptedException, SAXException, IOException {
//        new HouseDetailCrawlerJob().start();
//    }

    public void start() {
        int length = Integer.MAX_VALUE;
        final WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        for (int p = 1; p < length; p++) {
            try {

                final HtmlPage page = webClient.getPage("http://www.howzf.com/esf/esfnSearch_csnew.htm?page=" + p);
                final String pageAsText = page.asXml();
                Document doc = Jsoup.parse(pageAsText);
                Elements e = doc.getElementsByClass("fl w480");
                if (length == Integer.MAX_VALUE) {

                    try {
                        String maxLength = doc.getElementsByClass("nlc_totalpage").get(0).getElementsByTag("font").get(0)
                                .text();
                        length = Integer.valueOf(maxLength);
                    } catch (Exception ex) {
                        logger.error(ex.toString(), e);
                        length = 1587;
                    }


                }

                for (int i = 0; i < e.size(); i++) {
                    String url = "http://www.howzf.com/" + e.get(i).attr("href");
                    saveSingle(webClient, url, e.get(i).text());
                }

            } catch (Exception e) {
                //继续
                logger.error(e.toString(), e);
            } finally {
                webClient.close();
            }
        }

    }

    public void saveSingle(WebClient webClient, String url, String title) {

        try  {
            webClient.getOptions().setCssEnabled(false);
            final HtmlPage page = webClient.getPage(url);
            final String pageAsText = page.asXml();
            Document doc = Jsoup.parse(pageAsText);
            Elements e1 = doc.getElementsByClass("w450 fl");
            Elements e2 = doc.getElementsByClass("gpjlBox clearfix f14");

            String[] titleArr = title.split(" ");
//            System.out.println("###" + titleArr[0]);//小区
//            System.out.println("###" + titleArr[1]);//室
//            System.out.println("###" + titleArr[2]);//卫
//            System.out.println("###" + titleArr[3]);//厅
//            System.out.println("###" + titleArr[4]);//面积 ㎡


            Element e = e1.get(0);
            String address = getSiblingByKeyWord(e, "小区地址");
            String[] addrArr = address.split("-");

            double area = Double.parseDouble(StringUtils.getFrontString(titleArr[4], "㎡").substring(2));
            double unitPrice = Double.parseDouble(StringUtils.getFrontString(getByKeyWord(e, "元/㎡"), "元/㎡"));


            HouseDetail h = new HouseDetail();
            h.setUuid(UuidUtils.getUuid());
            h.setAddress(getSiblingByKeyWord(e, "小区地址"));
            h.setArea(StringUtils.limitLength(area + "", 10));
            h.setSumPrice(StringUtils.limitLength(area * unitPrice + "", 10));
            h.setUnitPrice(StringUtils.limitLength(unitPrice + "", 10));
            h.setCode(getSiblingByKeyWord(e, "核验编号"));
            h.setCreateDate(new Date());
            h.setDistrict(addrArr[0].substring(1, addrArr[0].length() - 1));
            h.setPlate(addrArr[1].substring(1, addrArr[0].length() - 1));
            h.setNeighbourhood(addrArr[2]);
            h.setFloor(getSiblingByKeyWord(e, "所在楼层"));
            h.setHouseType(getSiblingByKeyWord(e, "房屋类型"));
            h.setLayout(titleArr[1] + titleArr[2] + titleArr[3]);
            h.setLinkUrl(url);
            h.setListingTime(getSiblingByKeyWord(e, "挂牌时间"));
            h.setOrientation(getSiblingByKeyWord(e, "房屋朝向"));
            h.setOwnYear(getSiblingByKeyWord(e, "产权年限"));
            h.setRenovation(getSiblingByKeyWord(e, "装修程度"));
            h.setSchool(getSiblingByKeyWord(e, "周边学校"));
            h.setSubway(getSiblingByKeyWord(e, "周边地铁"));

            h.setYears(getSiblingByKeyWord(e, "建筑年代"));


            //挂牌历史
            String listingHistory = CommonConstants.BLANK_STR;
            Elements shelfList = e2.get(0).getElementsContainingOwnText("上架");
            for (Element shelfElement : shelfList) {
                Element p = shelfElement.parent();
                listingHistory = p.child(0).text() + "," + shelfElement.text() + ";";
            }

            h.setListingHistory(listingHistory);
            houseDetailMapper.insert(h);
            //休息500ms
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception e) {
            //继续
            logger.error(e.toString(), e);
        }
    }

    /**
     * 根据关键字获取元素的值
     *
     * @param element element
     * @param key     key
     * @return String
     */
    public String getByKeyWord(Element element, String key) {
        String value;
        try {
            value = element.getElementsContainingOwnText(key).get(0).text();
            if (StringUtils.isBlank(value)) {
                value = CommonConstants.BLANK_STR;
            }
        } catch (Exception e) {
            value = CommonConstants.BLANK_STR;
        }

        return value;

    }

    public String getSiblingByKeyWord(Element element, String key) {
        String value;
        try {
            value = element.getElementsContainingOwnText(key).get(0).nextElementSibling().text();
            if (StringUtils.isBlank(value)) {
                value = CommonConstants.BLANK_STR;
            }
        } catch (Exception e) {
            value = CommonConstants.BLANK_STR;
        }

        return value;

    }
}
