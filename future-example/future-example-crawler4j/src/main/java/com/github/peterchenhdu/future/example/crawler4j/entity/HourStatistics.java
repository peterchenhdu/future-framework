/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.crawler4j.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 小时统计表 - 实体类
 * @author PiChen
 * @since 2018-08-13 23:29:33
 */
@Table(name = "crawler4j.hour_statistics")
public class HourStatistics {
    /**
     * UUID
     */
    @Id
    private String uuid;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 区域ID
     */
    @Column(name = "area_id")
    private String areaId;

    /**
     * 挂牌真房源
     */
    @Column(name = "house_sum")
    private Long houseSum;

    /**
     * 备案经纪人
     */
    @Column(name = "agent_sum")
    private Long agentSum;

    /**
     * 金领顾问
     */
    @Column(name = "gold_consultant_sum")
    private Long goldConsultantSum;

    /**
     * 今日挂牌
     */
    @Column(name = "today_list_count")
    private Long todayListCount;

    /**
     * 今日签约
     */
    @Column(name = "today_sign")
    private Long todaySign;

    /**
     * 获取UUID
     *
     * @return uuid - UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置UUID
     *
     * @param uuid UUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取区域ID
     *
     * @return area_id - 区域ID
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * 设置区域ID
     *
     * @param areaId 区域ID
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    /**
     * 获取挂牌真房源
     *
     * @return house_sum - 挂牌真房源
     */
    public Long getHouseSum() {
        return houseSum;
    }

    /**
     * 设置挂牌真房源
     *
     * @param houseSum 挂牌真房源
     */
    public void setHouseSum(Long houseSum) {
        this.houseSum = houseSum;
    }

    /**
     * 获取备案经纪人
     *
     * @return agent_sum - 备案经纪人
     */
    public Long getAgentSum() {
        return agentSum;
    }

    /**
     * 设置备案经纪人
     *
     * @param agentSum 备案经纪人
     */
    public void setAgentSum(Long agentSum) {
        this.agentSum = agentSum;
    }

    /**
     * 获取金领顾问
     *
     * @return gold_consultant_sum - 金领顾问
     */
    public Long getGoldConsultantSum() {
        return goldConsultantSum;
    }

    /**
     * 设置金领顾问
     *
     * @param goldConsultantSum 金领顾问
     */
    public void setGoldConsultantSum(Long goldConsultantSum) {
        this.goldConsultantSum = goldConsultantSum;
    }

    /**
     * 获取今日挂牌
     *
     * @return today_list_count - 今日挂牌
     */
    public Long getTodayListCount() {
        return todayListCount;
    }

    /**
     * 设置今日挂牌
     *
     * @param todayListCount 今日挂牌
     */
    public void setTodayListCount(Long todayListCount) {
        this.todayListCount = todayListCount;
    }

    /**
     * 获取今日签约
     *
     * @return today_sign - 今日签约
     */
    public Long getTodaySign() {
        return todaySign;
    }

    /**
     * 设置今日签约
     *
     * @param todaySign 今日签约
     */
    public void setTodaySign(Long todaySign) {
        this.todaySign = todaySign;
    }
}