/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * File Name: DayEndJob.java

 * Description:
 * Author: Pi Chen
 * Create Date: 2016年9月8日
 *
 */
package com.github.peterchenhdu.future.tool.mb.crawler4j.core.quartzjobs;

import com.github.peterchenhdu.future.tool.mb.crawler4j.common.constant.BFConstant;
import com.github.peterchenhdu.future.tool.mb.crawler4j.common.log.Logger;
import com.github.peterchenhdu.future.tool.mb.crawler4j.model.User;
import com.github.peterchenhdu.future.tool.mb.crawler4j.service.user.IUserService;
import com.github.peterchenhdu.future.tool.mb.crawler4j.service.user.impl.UserServiceImpl;
import com.github.peterchenhdu.future.tool.mb.crawler4j.util.ProjectConfigUtil;
import com.github.peterchenhdu.future.util.SpringContextUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Properties;
import java.util.Random;
import java.util.UUID;


/**
 * 每天 执行一次
 *
 * @author Pi Chen
 * @version infosys V1.0.0, 2016年9月8日
 * @see
 * @since infosys V1.0.0
 */

public class AddUserJob extends QuartzJobBean {

    private static Logger logger = Logger.getLogger(AddUserJob.class);

    /**
     * 每天具体Job
     *
     * @param context
     * @throws JobExecutionException
     * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            Properties properties = ProjectConfigUtil.getConfig();
            if (BFConstant.TRUE.equals(properties.get("saveUserJob"))) {
                IUserService userService = SpringContextUtils.getBean("userService", UserServiceImpl.class);
                logger.info("start calling saveUser.");
                User user = new User();
                user.setAddress(new Random().nextInt(10000) + "");
                user.setName(UUID.randomUUID().toString());
                userService.saveUser(user);
                logger.info("end up calling saveUser.");
            }
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }

    }

}
