/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.tool.mb.crawler4j.service.news;

import com.github.peterchenhdu.future.tool.mb.crawler4j.model.News;

import java.util.List;


/**
 * @author Pi Chen
 * @version infosys V1.0.0, 2016年12月10日
 * @see
 * @since infosys V1.0.0
 */
public interface INewsService {

    public News saveNews(News news);

    public List<News> findByNews(News news);

    public List<News> findByMonth(String month, long offset, long limit);

    public long getYearCount(int year);

    public long getCount(String month);
}
