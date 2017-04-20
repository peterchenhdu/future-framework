package net.jforum.search.dto;

import java.util.Date;

import net.jforum.util.preferences.ConfigKeys;
import net.jforum.util.preferences.SystemGlobals;

/**
 * 搜索参数
 * 
 * @author PiChen
 * @version forum4j V1.0.0, 2017年4月15日 下午10:40:04
 * @see       
 * @since forum4j V1.0.0
 */
public class SearchArgs 
{
    /**
     * 关键字
     */
	private String keywords;
	private int author;
	private String orderDir = "DESC";
	private String orderBy;
	private boolean matchAllKeywords;
	private int forumId;
	private int initialRecord;
	private Date fromDate;
	private Date toDate;
	private String matchType;
	
	public void setMatchType(String matchType)
	{
		this.matchType = matchType;
	}
	
	public String getMatchType()
	{
		return this.matchType;
	}
	
	public void setDateRange(Date fromDate, Date toDate)
	{
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	
	public Date getFromDate()
	{
		return this.fromDate;
	}
	
	public Date getToDate()
	{
		return this.toDate;
	}
	
	public int fetchCount()
	{
		return SystemGlobals.getIntValue(ConfigKeys.TOPICS_PER_PAGE);
	}
	
	public void startFetchingAtRecord(int initialRecord)
	{
		this.initialRecord = initialRecord;
	}
	
	public int startFrom()
	{
		return this.initialRecord;
	}
	
	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}
	
	public void matchAllKeywords()
	{
		this.matchAllKeywords = true;
	}
	
	public void setAuthor(int author)
	{
		this.author = author;
	}
	
	public void setForumId(int forumId)
	{
		this.forumId = forumId;
	}
	
	public void setOrderBy(String orderBy)
	{
		this.orderBy = orderBy;
	}
	
	public void setOrderDir(String orderDir)
	{
		this.orderDir = orderDir;
	}
	
	public String[] getKeywords()
	{
		if (this.keywords == null || this.keywords.trim().length() == 0) {
			return new String[] {};
		}

		return this.keywords.trim().split(" ");
	}
	
	public String rawKeywords()
	{
		if (this.keywords == null) {
			return "";
		}
		
		return this.keywords.trim();
	}
	
	public boolean shouldMatchAllKeywords()
	{
		return this.matchAllKeywords;
	}
	
	public int getAuthor()
	{
		return this.author;
	}
	
	public int getForumId()
	{
		return this.forumId;
	}
	
	public String getOrderDir()
	{
		if (!"ASC".equals(this.orderDir) && !"DESC".equals(this.orderDir)) {
			return "DESC";
		}

		return this.orderDir;
	}
	
	public String getOrderBy()
	{
		return this.orderBy;
	}
}
