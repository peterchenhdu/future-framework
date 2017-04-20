
package net.jforum.search.dto;

import java.util.List;

/**
 * 搜索结果
 * 
 * @author PiChen
 * @version forum4j V1.0.0, 2017年4月15日 下午10:30:28
 * @see       
 * @since forum4j V1.0.0
 */
public class SearchResult<T>
{
    private List<T> records;
    private int numberOfHits;

    public SearchResult(List<T> records, int numberOfHits)
    {
        this.records = records;
        this.numberOfHits = numberOfHits;
    }

    public List<T> records()
    {
        return this.records;
    }

    public int numberOfHits()
    {
        return this.numberOfHits;
    }
}
