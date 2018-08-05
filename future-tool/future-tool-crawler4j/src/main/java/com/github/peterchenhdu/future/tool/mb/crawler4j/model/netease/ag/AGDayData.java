/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.tool.mb.crawler4j.model.netease.ag;

/**
 * 每日AG数据
 *
 * @author Pi Chen
 * @ClassName: AGDayData
 * @Description: TODO
 * @date 2016年8月11日 下午7:55:46
 */
public class AGDayData {
    private String wareName;
    private float lastClosePrice;
    private Object[][] data;

    public String getWareName() {
        return wareName;
    }

    public float getLastClosePrice() {
        return lastClosePrice;
    }

    public Object[][] getData() {
        return data;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public void setLastClosePrice(float lastClosePrice) {
        this.lastClosePrice = lastClosePrice;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

}
