/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.tool.mb.crawler4j.core.listener;

import javax.servlet.ServletContextEvent;

import com.github.peterchenhdu.future.tool.mb.crawler4j.common.log.Logger;
import org.springframework.web.context.ContextLoaderListener;


/**
 * @author Pi Chen
 * @version infosys V1.0.0, 2016年5月23日
 * @see
 * @since infosys V1.0.0
 */
public class InitialListener extends ContextLoaderListener {
    private static Logger logger = Logger.getLogger(InitialListener.class);

    /**
     * @param arg0
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        logger.info("start contextDestroyed.");
        super.contextDestroyed(event);

    }

    /**
     * @param arg0
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.info("start contextInitialized.");

        super.contextInitialized(event);

    }

}
