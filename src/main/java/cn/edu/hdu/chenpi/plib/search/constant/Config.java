/*
 * File Name: Config.java
 * Description: 
 * Author: PiChen
 * Create Date: 2017年4月16日

 */
package net.jforum.search.constant;

/**
 * 
 * @author PiChen
 * @version forum4j V1.0.0, 2017年4月16日 下午4:54:42
 * @see
 * @since forum4j V1.0.0
 */

public class Config
{

    public static final String LUCENE_ANALYZER = "org.apache.lucene.analysis.standard.StandardAnalyzer";
    //索引存放目录
    public static final String LUCENE_INDEX_WRITE_PATH = "F:/data/forumIndex";
    //重建索引时，一次性从数据库读取数据的数目
    public static final int LUCENE_INDEXER_DB_FETCH_COUNT = 50 ;
    //内存中的doc数目
    public static final int LUCENE_INDEXER_RAM_NUMDOCS = 1000;
}
