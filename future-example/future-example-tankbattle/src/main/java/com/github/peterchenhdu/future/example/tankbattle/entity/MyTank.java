/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.tankbattle.entity;

import com.github.peterchenhdu.future.example.tankbattle.enums.DirectionEnum;
import com.github.peterchenhdu.future.example.tankbattle.enums.TankTypeEnum;

import java.awt.Color;

/**
 * MyTank...
 *
 * @author chenpi
 * @since 2011-02-10 19:29
 */
public class MyTank extends Tank {
    /**
     * 构造方法
     *
     * @param x      x坐标
     * @param y      y坐标
     * @param direct 方向
     */
    public MyTank(int x, int y, DirectionEnum direct) {
        super(x, y, direct);
        this.setColor(Color.yellow);
        this.setTankType(TankTypeEnum.MY);
        this.setBlood(10);
    }


}