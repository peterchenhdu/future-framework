/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.tankbattle.thread.task;

import com.github.peterchenhdu.future.example.tankbattle.entity.EnemyTank;
import com.github.peterchenhdu.future.example.tankbattle.service.GameEventService;

import java.util.TimerTask;

/**
 * MyTimerTask...
 *
 * @author chenpi
 * @since 2011-02-10 19:29
 */
public class EnemyTankAutoShotTask extends TimerTask {
    EnemyTank tank;
    GameEventService gameEventService;

    public EnemyTankAutoShotTask(EnemyTank tank, GameEventService gameEventService) {
        this.tank = tank;
        this.gameEventService = gameEventService;
    }

    @Override
    public void run() {
        if (tank.getSpeedVector() == 0 && tank.isShot() && tank.activate()) {
            gameEventService.shot(tank);
        }

    }

}