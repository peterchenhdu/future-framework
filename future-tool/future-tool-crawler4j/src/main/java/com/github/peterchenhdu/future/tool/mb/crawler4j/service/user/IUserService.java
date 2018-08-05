/*
 * Copyright (c) 2011-2025 PiChen
 */
package com.github.peterchenhdu.future.tool.mb.crawler4j.service.user;

import com.github.peterchenhdu.future.tool.mb.crawler4j.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;


/**
 * @author Pi Chen
 * @version infosys V1.0.0, 2016年5月24日
 * @see
 * @since infosys V1.0.0
 */

public interface IUserService {


    public List<User> query(Map<String, Object> param);

    @Cacheable(value = "userCache", key = "#id")
    public User findById(long id);

    @CachePut(value = "userCache", key = "#result.id")
    public User saveUser(User user);

    //注意key的类型要一致，不要一个是long，一个object or string
    @CacheEvict(value = "userCache", key = "#id")
    public void deleteUser(long id);

    public User updateUser(User user);
}
