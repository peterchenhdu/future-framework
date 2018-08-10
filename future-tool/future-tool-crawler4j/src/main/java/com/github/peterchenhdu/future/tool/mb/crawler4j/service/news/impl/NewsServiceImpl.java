/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.tool.mb.crawler4j.service.news.impl;

import com.github.peterchenhdu.future.tool.mb.crawler4j.common.base.BaseService;
import com.github.peterchenhdu.future.tool.mb.crawler4j.crawler4j.dao.NewsMapper;
import com.github.peterchenhdu.future.tool.mb.crawler4j.crawler4j.model.News;
import com.github.peterchenhdu.future.tool.mb.crawler4j.service.news.INewsService;
import com.github.peterchenhdu.future.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("newsServiceImpl")
public class NewsServiceImpl extends BaseService implements INewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public int saveNews(News news) {
        news.setUuid(UuidUtils.getUuid());
        return newsMapper.insertSelective(news);
    }

}
