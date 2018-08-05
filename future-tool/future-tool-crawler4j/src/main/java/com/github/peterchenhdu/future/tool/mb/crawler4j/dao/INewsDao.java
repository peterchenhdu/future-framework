/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.tool.mb.crawler4j.dao;

import com.github.peterchenhdu.future.tool.mb.crawler4j.model.News;

import java.util.List;
import java.util.Map;


/**
 * @author Pi Chen
 * @version infosys V1.0.0, 2016年12月10日
 * @see
 * @since infosys V1.0.0
 */

public interface INewsDao {
    public int saveNewsCont(Map<String, Object> param);

    public int saveNewsSum(Map<String, Object> param);

    public List<News> findByNews(Map<String, Object> param);

    public void createNewTableCont(String tableName);

    public void createNewTableSum(String tableName);

    public List<News> findByTime(String tableName,
                                 String from, String to,
                                 long offset, long limit);

    public long getCount(Map<String, Object> param);

}
