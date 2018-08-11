/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.tankbattle.context;

import com.github.peterchenhdu.future.example.tankbattle.constant.GameConstants;
import com.github.peterchenhdu.future.example.tankbattle.enums.DirectionEnum;
import com.github.peterchenhdu.future.example.tankbattle.listener.MainFrameMouseListener;
import com.github.peterchenhdu.future.example.tankbattle.service.GameEventService;
import com.github.peterchenhdu.future.example.tankbattle.service.PaintService;
import com.github.peterchenhdu.future.example.tankbattle.dto.RealTimeGameData;
import com.github.peterchenhdu.future.example.tankbattle.enums.LevelEnum;
import com.github.peterchenhdu.future.example.tankbattle.entity.EnemyTank;
import com.github.peterchenhdu.future.example.tankbattle.entity.MyTank;
import com.github.peterchenhdu.future.example.tankbattle.thread.executor.TaskExecutor;
import com.github.peterchenhdu.future.example.tankbattle.thread.task.GameUpdateTask;
import com.github.peterchenhdu.future.example.tankbattle.listener.MainFrameKeyListener;
import com.github.peterchenhdu.future.example.tankbattle.listener.MenuActionListener;
import com.github.peterchenhdu.future.example.tankbattle.view.frame.GameFrame;
import com.github.peterchenhdu.future.example.tankbattle.view.menubar.TankBattleMenuBar;
import com.github.peterchenhdu.future.example.tankbattle.view.panel.GamePanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Class Description...
 *
 * @author chenpi
 * @since 2018/3/19 20:32
 */
@Component
public class GameContext {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Frame
     */
    private GameFrame gameFrame;
    /**
     * 菜单条
     */
    private TankBattleMenuBar tankBattleMenuBar;
    /**
     * Panel
     */
    private GamePanel gamePanel;

    /**
     * RealTimeGameData
     */
    private RealTimeGameData gameData;

    @Autowired
    private MainFrameKeyListener mainFrameKeyListener;
    @Autowired
    private MenuActionListener menuActionListener;
    @Autowired
    private MainFrameMouseListener mainFrameMouseListener;
    @Autowired
    private GameEventService control;

    @Autowired
    private PaintService paintService;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private TaskExecutor threadTaskExecutor;

    @PostConstruct
    public void init() {
        logger.info("GameContext...");

        initGameData(1);


        this.gameFrame = new GameFrame();
        this.tankBattleMenuBar = new TankBattleMenuBar(menuActionListener);
        this.gamePanel = new GamePanel(paintService);

        this.gameFrame.setJMenuBar(this.tankBattleMenuBar);
        this.gameFrame.add(this.gamePanel);
        this.gameFrame.addKeyListener(mainFrameKeyListener);
        this.gamePanel.addMouseListener(mainFrameMouseListener);

        this.gameFrame.setVisible(true);

        logger.info("execute UpdateTask...");
        taskExecutor.execute(new GameUpdateTask(this));

        logger.info("game start success...");

    }


    public void initGameData(int level) {
        gameData = new RealTimeGameData();

        for (int i = 0; i < GameConstants.INIT_ENEMY_TANK_IN_MAP_NUM; i++) {
            EnemyTank enemy = new EnemyTank((i) * 140 + 20, -20, DirectionEnum.SOUTH);
            enemy.setLocation(i);
            gameData.getEnemies().add(enemy);
        }
        for (int i = 0; i < 1; i++) {
            MyTank myTank = new MyTank(300, 620, DirectionEnum.NORTH);
            gameData.getMyTanks().add(myTank);
        }

        gameData.setMap(LevelEnum.getByLevel(level).getMap());
        //gameData.setMap(new Map(MapParser.getMapFromXml()));


        gameData.setEnemyTankNum(GameConstants.INIT_ENEMY_TANK_NUM);
        gameData.setMyTankNum(GameConstants.INIT_MY_TANK_NUM);
        gameData.setMyBulletNum(GameConstants.MY_TANK_INIT_BULLET_NUM);
        gameData.setBeKilled(0);
        gameData.setDy(600);
        gameData.setLevel(level);
        threadTaskExecutor.startEnemyTankThreads();
        logger.info("init Game Data...");
    }


    public void reset(int level) {
        gameData.reset();

        for (int i = 0; i < GameConstants.INIT_ENEMY_TANK_IN_MAP_NUM; i++) {
            EnemyTank enemy = new EnemyTank((i) * 140 + 20, -20, DirectionEnum.SOUTH);
            enemy.setLocation(i);
            gameData.getEnemies().add(enemy);
        }
        for (int i = 0; i < 1; i++) {
            MyTank myTank = new MyTank(300, 620, DirectionEnum.NORTH);
            gameData.getMyTanks().add(myTank);
        }

        gameData.setMap(LevelEnum.getByLevel(level).getMap());


        gameData.setEnemyTankNum(GameConstants.INIT_ENEMY_TANK_NUM);
        gameData.setMyTankNum(GameConstants.INIT_MY_TANK_NUM);
        gameData.setMyBulletNum(GameConstants.MY_TANK_INIT_BULLET_NUM);
        gameData.setBeKilled(0);
        gameData.setDy(600);
        gameData.setLevel(level);
        threadTaskExecutor.startEnemyTankThreads();
        logger.info("init Game Data...");
    }


    public void startGame() {
        gameData.setStart(Boolean.TRUE);
        gameData.getEnemies().forEach(t -> t.setActivate(Boolean.TRUE));
        gameData.getMyTanks().forEach(t -> t.setActivate(Boolean.TRUE));
    }

    public void startLevel(int level) {
        reset(level);
        this.startGame();
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public TankBattleMenuBar getTankBattleMenuBar() {
        return tankBattleMenuBar;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public RealTimeGameData getGameData() {
        return gameData;
    }

    public GameEventService getControl() {
        return control;
    }


}
