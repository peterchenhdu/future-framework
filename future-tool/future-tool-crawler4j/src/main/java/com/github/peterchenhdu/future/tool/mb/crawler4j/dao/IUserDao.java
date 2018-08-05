/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.tool.mb.crawler4j.dao;

import com.github.peterchenhdu.future.tool.mb.crawler4j.model.User;

import java.util.List;
import java.util.Map;


/**
 * @author Pi Chen
 * @version infosys V1.0.0, 2016年8月12日
 * @see
 * @since infosys V1.0.0
 */

public interface IUserDao {
    public List<User> query(Map<String, Object> param);

    public int saveUser(Map<String, Object> param);

    public void deleteUser(Map<String, Object> param);

    public int updateUser(User user);
}
