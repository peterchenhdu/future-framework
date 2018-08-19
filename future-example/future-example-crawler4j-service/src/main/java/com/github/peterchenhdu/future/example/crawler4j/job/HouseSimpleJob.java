/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.job;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.github.peterchenhdu.future.example.crawler4j.entity.HouseSimple;
import com.github.peterchenhdu.future.example.crawler4j.mapper.HouseSimpleMapper;
import com.github.peterchenhdu.future.util.ObjectUtils;
import com.github.peterchenhdu.future.util.UuidUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Date;

/**
 * @author PiChen
 * @since 2018/8/19 23:32
 */

public class HouseSimpleJob implements Job, Serializable {
    private final static Logger logger = LoggerFactory.getLogger(HouseSimpleJob.class);
    @Autowired
    private HouseSimpleMapper houseSimpleMapper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //最后一页接下来，仍然会请求最后一页
        l:for (int p = 0; p < Integer.MAX_VALUE; p++) {
            try (final WebClient webClient = new WebClient()) {
                final HtmlPage page = webClient.getPage("http://www.howzf.com/esf/esfnSearch_csnew.htm?page=" + p);
                final String pageAsText = page.asXml();
                Document doc = Jsoup.parse(pageAsText);
                Elements e = doc.getElementsByClass("fl w480");

                for (int i = 0; i < e.size(); i++) {
                    String url = "http://www.howzf.com/" + e.get(i).attr("href");
                    HouseSimple h = new HouseSimple();
                    h.setUuid(UuidUtils.getUuid());
                    h.setCreateTime(new Date());
                    h.setTitle(e.get(i).text());
                    h.setUrl(url);

                    Example example = new Example(HouseSimple.class);
                    example.createCriteria().andEqualTo("url", h.getUrl());
                    if(ObjectUtils.isNotEmpty(houseSimpleMapper.selectByExample(example))){
                        logger.info("爬取结束，跳出");
                        break l;
                    }
                    houseSimpleMapper.insert(h);
                }

            } catch (Exception e) {
                //继续
                logger.error(e.toString(), e);
            }
        }

    }
}
