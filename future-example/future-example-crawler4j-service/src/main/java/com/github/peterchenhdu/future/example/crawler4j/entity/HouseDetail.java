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
 * @since 2018-08-18 19:46:15
 */
@Table(name = "crawler4j.house_detail")
public class HouseDetail {
    @Id
    private String uuid;

    @Column(name = "link_url")
    private String linkUrl;

    /**
     * 平方米 - ㎡
     */
    private String area;

    /**
     * 2室1厅2卫
     */
    private String layout;

    /**
     * 每平米单价
     */
    @Column(name = "unit_price")
    private String unitPrice;

    @Column(name = "sum_price")
    private String sumPrice;

    /**
     * 所在楼层： 高层（共7层）
     */
    private String floor;

    /**
     * 建筑年代： 2000年
     */
    private String years;

    /**
     * 房屋类型： 住宅
     */
    @Column(name = "house_type")
    private String houseType;

    /**
     * 房屋朝向： 朝南
     */
    private String orientation;

    /**
     * 产权年限： 70年
     */
    @Column(name = "own_year")
    private String ownYear;

    /**
     * 装修程度： 精装修
     */
    private String renovation;

    /**
     * 核验编号：180310457353
     */
    private String code;

    /**
     * 挂牌时间：2018-08-17
     */
    @Column(name = "listing_time")
    private String listingTime;

    /**
     * 周边学校：ssss
     */
    private String school;

    /**
     * 周边地铁：
     */
    private String subway;

    /**
     * 区地址：[下城 - 三塘
     */
    private String address;

    /**
     * 余杭区
     */
    private String district;

    /**
     * 板块
     */
    private String plate;

    /**
     * 小区
     */
    private String neighbourhood;

    @Column(name = "create_date")
    private Date createDate;

    /**
     * 挂牌历史
     */
    @Column(name = "listing_history")
    private String listingHistory;

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
     * @return link_url
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * @param linkUrl
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    /**
     * 获取平方米 - ㎡
     *
     * @return area - 平方米 - ㎡
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置平方米 - ㎡
     *
     * @param area 平方米 - ㎡
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * 获取2室1厅2卫
     *
     * @return layout - 2室1厅2卫
     */
    public String getLayout() {
        return layout;
    }

    /**
     * 设置2室1厅2卫
     *
     * @param layout 2室1厅2卫
     */
    public void setLayout(String layout) {
        this.layout = layout == null ? null : layout.trim();
    }

    /**
     * 获取每平米单价
     *
     * @return unit_price - 每平米单价
     */
    public String getUnitPrice() {
        return unitPrice;
    }

    /**
     * 设置每平米单价
     *
     * @param unitPrice 每平米单价
     */
    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice == null ? null : unitPrice.trim();
    }

    /**
     * @return sum_price
     */
    public String getSumPrice() {
        return sumPrice;
    }

    /**
     * @param sumPrice
     */
    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice == null ? null : sumPrice.trim();
    }

    /**
     * 获取所在楼层： 高层（共7层）
     *
     * @return floor - 所在楼层： 高层（共7层）
     */
    public String getFloor() {
        return floor;
    }

    /**
     * 设置所在楼层： 高层（共7层）
     *
     * @param floor 所在楼层： 高层（共7层）
     */
    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    /**
     * 获取建筑年代： 2000年
     *
     * @return years - 建筑年代： 2000年
     */
    public String getYears() {
        return years;
    }

    /**
     * 设置建筑年代： 2000年
     *
     * @param years 建筑年代： 2000年
     */
    public void setYears(String years) {
        this.years = years == null ? null : years.trim();
    }

    /**
     * 获取房屋类型： 住宅
     *
     * @return house_type - 房屋类型： 住宅
     */
    public String getHouseType() {
        return houseType;
    }

    /**
     * 设置房屋类型： 住宅
     *
     * @param houseType 房屋类型： 住宅
     */
    public void setHouseType(String houseType) {
        this.houseType = houseType == null ? null : houseType.trim();
    }

    /**
     * 获取房屋朝向： 朝南
     *
     * @return orientation - 房屋朝向： 朝南
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * 设置房屋朝向： 朝南
     *
     * @param orientation 房屋朝向： 朝南
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation == null ? null : orientation.trim();
    }

    /**
     * 获取产权年限： 70年
     *
     * @return own_year - 产权年限： 70年
     */
    public String getOwnYear() {
        return ownYear;
    }

    /**
     * 设置产权年限： 70年
     *
     * @param ownYear 产权年限： 70年
     */
    public void setOwnYear(String ownYear) {
        this.ownYear = ownYear == null ? null : ownYear.trim();
    }

    /**
     * 获取装修程度： 精装修
     *
     * @return renovation - 装修程度： 精装修
     */
    public String getRenovation() {
        return renovation;
    }

    /**
     * 设置装修程度： 精装修
     *
     * @param renovation 装修程度： 精装修
     */
    public void setRenovation(String renovation) {
        this.renovation = renovation == null ? null : renovation.trim();
    }

    /**
     * 获取核验编号：180310457353
     *
     * @return code - 核验编号：180310457353
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置核验编号：180310457353
     *
     * @param code 核验编号：180310457353
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取挂牌时间：2018-08-17
     *
     * @return listing_time - 挂牌时间：2018-08-17
     */
    public String getListingTime() {
        return listingTime;
    }

    /**
     * 设置挂牌时间：2018-08-17
     *
     * @param listingTime 挂牌时间：2018-08-17
     */
    public void setListingTime(String listingTime) {
        this.listingTime = listingTime == null ? null : listingTime.trim();
    }

    /**
     * 获取周边学校：ssss
     *
     * @return school - 周边学校：ssss
     */
    public String getSchool() {
        return school;
    }

    /**
     * 设置周边学校：ssss
     *
     * @param school 周边学校：ssss
     */
    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    /**
     * 获取周边地铁：
     *
     * @return subway - 周边地铁：
     */
    public String getSubway() {
        return subway;
    }

    /**
     * 设置周边地铁：
     *
     * @param subway 周边地铁：
     */
    public void setSubway(String subway) {
        this.subway = subway == null ? null : subway.trim();
    }

    /**
     * 获取区地址：[下城 - 三塘
     *
     * @return address - 区地址：[下城 - 三塘
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置区地址：[下城 - 三塘
     *
     * @param address 区地址：[下城 - 三塘
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取余杭区
     *
     * @return district - 余杭区
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置余杭区
     *
     * @param district 余杭区
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * 获取板块
     *
     * @return plate - 板块
     */
    public String getPlate() {
        return plate;
    }

    /**
     * 设置板块
     *
     * @param plate 板块
     */
    public void setPlate(String plate) {
        this.plate = plate == null ? null : plate.trim();
    }

    /**
     * 获取小区
     *
     * @return neighbourhood - 小区
     */
    public String getNeighbourhood() {
        return neighbourhood;
    }

    /**
     * 设置小区
     *
     * @param neighbourhood 小区
     */
    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood == null ? null : neighbourhood.trim();
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取挂牌历史
     *
     * @return listing_history - 挂牌历史
     */
    public String getListingHistory() {
        return listingHistory;
    }

    /**
     * 设置挂牌历史
     *
     * @param listingHistory 挂牌历史
     */
    public void setListingHistory(String listingHistory) {
        this.listingHistory = listingHistory == null ? null : listingHistory.trim();
    }
}