/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.tankbattle.entity;

import com.github.peterchenhdu.future.example.tankbattle.enums.StuffTypeEnum;

/**
 * Water...
 *
 * @author chenpi
 * @since 2011-02-10 19:29
 */
public class Water extends Stuff {
    /**
     * 构造方法
     *
     * @param x x坐标
     * @param y y坐标
     */

    public Water(int x, int y) {
        super(x, y);
        this.setType(StuffTypeEnum.WATER);
        this.setWidth(20);
        this.setHeight(20);
    }

}
