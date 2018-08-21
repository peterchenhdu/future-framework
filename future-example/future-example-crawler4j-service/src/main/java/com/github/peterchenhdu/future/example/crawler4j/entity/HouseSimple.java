/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * null - 实体类
 * @author PiChen
 * @since 2018-08-21 22:56:56
 */
@Table(name = "crawler4j.house_simple")
public class HouseSimple {
    @Id
    private String uuid;

    private String url;

    @Column(name = "create_time")
    private Date createTime;

    private String title;

    @Column(name = "visited_flag")
    private Short visitedFlag;

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
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
     * @return visited_flag
     */
    public Short getVisitedFlag() {
        return visitedFlag;
    }

    /**
     * @param visitedFlag
     */
    public void setVisitedFlag(Short visitedFlag) {
        this.visitedFlag = visitedFlag;
    }
}