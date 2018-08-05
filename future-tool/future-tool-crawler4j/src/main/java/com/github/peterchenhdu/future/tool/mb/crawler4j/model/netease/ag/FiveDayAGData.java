/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.tool.mb.crawler4j.model.netease.ag;

/**
 * 五日AG数据
 *
 * @author Pi Chen
 * @ClassName: FiveDayAGData
 * @Description: TODO
 * @date 2016年8月11日 下午7:56:30
 */
public class FiveDayAGData {

    private int num;
    private String retCode;
    private String retDesc;
    private String version;
    private AGDayData[] ret;

    public int getNum() {
        return num;
    }

    public String getRetCode() {
        return retCode;
    }

    public String getRetDesc() {
        return retDesc;
    }

    public String getVersion() {
        return version;
    }

    public AGDayData[] getRet() {
        return ret;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public void setRetDesc(String retDesc) {
        this.retDesc = retDesc;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setRet(AGDayData[] ret) {
        this.ret = ret;
    }
}
