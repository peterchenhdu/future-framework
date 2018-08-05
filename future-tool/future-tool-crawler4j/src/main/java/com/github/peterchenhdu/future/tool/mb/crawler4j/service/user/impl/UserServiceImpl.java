/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.tool.mb.crawler4j.service.user.impl;

import com.github.peterchenhdu.future.tool.mb.crawler4j.dao.IUserDao;
import com.github.peterchenhdu.future.tool.mb.crawler4j.model.User;
import com.github.peterchenhdu.future.tool.mb.crawler4j.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Pi Chen
 * @version infosys V1.0.0, 2016年5月24日
 * @see
 * @since infosys V1.0.0
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    /**
     * @return
     * @throws Exception
     */
    @Override
    public List<User> query(Map<String, Object> param) {
        return userDao.query(param);
    }

    /**
     * @throws Exception
     */
    @Override
    public User saveUser(User user) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name", user.getName());
        param.put("address", user.getAddress());
        userDao.saveUser(param);
        user.setId((long) param.get("id"));
        return user;

    }

    /**
     * @throws Exception
     */
    @Override
    public void deleteUser(long id) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        userDao.deleteUser(param);
    }

    /**
     * brief description detail description
     *
     * @param id
     */
    @Override
    public User findById(long id) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        List<User> users = this.query(param);
        return users.size() > 0 ? users.get(0) : null;
    }

    /**
     * brief description
     * detail description
     *
     * @param user
     * @return
     */
    @Override
    public User updateUser(User user) {
        userDao.updateUser(user);
        return user;
    }

}
