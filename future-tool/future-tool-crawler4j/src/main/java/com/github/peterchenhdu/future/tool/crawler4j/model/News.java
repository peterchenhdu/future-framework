/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.tool.crawler4j.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * null - 实体类
 *
 * @author PiChen
 * @since 2018-08-09 23:14:10
 */
@Table(name = "crawler4j.news")
public class News {
    @Id
    private String uuid;

    private String url;

    private String title;

    @Column(name = "content_text")
    private String contentText;

    @Column(name = "content_html")
    private String contentHtml;

    @Column(name = "src_url")
    private String srcUrl;

    @Column(name = "src_name")
    private String srcName;

    @Column(name = "publish_time")
    private String publishTime;

    @Column(name = "crawler_name")
    private String crawlerName;

    @Column(name = "crawler_src")
    private String crawlerSrc;

    /**
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @return content_text
     */
    public String getContentText() {
        return contentText;
    }

    /**
     * @param contentText
     */
    public void setContentText(String contentText) {
        this.contentText = contentText == null ? null : contentText.trim();
    }

    /**
     * @return content_html
     */
    public String getContentHtml() {
        return contentHtml;
    }

    /**
     * @param contentHtml
     */
    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml == null ? null : contentHtml.trim();
    }

    /**
     * @return src_url
     */
    public String getSrcUrl() {
        return srcUrl;
    }

    /**
     * @param srcUrl
     */
    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl == null ? null : srcUrl.trim();
    }

    /**
     * @return src_name
     */
    public String getSrcName() {
        return srcName;
    }

    /**
     * @param srcName
     */
    public void setSrcName(String srcName) {
        this.srcName = srcName == null ? null : srcName.trim();
    }

    /**
     * @return publish_time
     */
    public String getPublishTime() {
        return publishTime;
    }

    /**
     * @param publishTime
     */
    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime == null ? null : publishTime.trim();
    }

    /**
     * @return crawler_name
     */
    public String getCrawlerName() {
        return crawlerName;
    }

    /**
     * @param crawlerName
     */
    public void setCrawlerName(String crawlerName) {
        this.crawlerName = crawlerName == null ? null : crawlerName.trim();
    }

    /**
     * @return crawler_src
     */
    public String getCrawlerSrc() {
        return crawlerSrc;
    }

    /**
     * @param crawlerSrc
     */
    public void setCrawlerSrc(String crawlerSrc) {
        this.crawlerSrc = crawlerSrc == null ? null : crawlerSrc.trim();
    }
}