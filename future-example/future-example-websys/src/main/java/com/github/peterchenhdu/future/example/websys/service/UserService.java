/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.example.websys.service;

import com.github.peterchenhdu.future.example.websys.dto.UserDto;
import com.github.peterchenhdu.future.example.websys.entity.User;
import com.github.peterchenhdu.future.example.websys.mapper.IUserMapper;
import com.github.peterchenhdu.future.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author PiChen
 * @since 2018/8/12 23:03
 */
@Service
public class UserService {
    @Autowired
    private IUserMapper userMapper;

    public List<UserDto> selectUserList(UserDto user)
    {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(ObjectUtils.isNotEmpty(user.getLoginName())){
            criteria.andLike("loginName", "%" + user.getLoginName() + "%");
        }
        if(ObjectUtils.isNotEmpty(user.getStatus())){
            criteria.andEqualTo("status", user.getStatus());
        }
        if(ObjectUtils.isNotEmpty(user.getPhoneNumber())){
            criteria.andLike("phoneNumber", "%" + user.getPhoneNumber() + "%");
        }

        List<User> list = userMapper.selectByExample(example);
        System.out.println(list);
        return null;
    }

    @PostConstruct
    public void test(){
        selectUserList(new UserDto());
    }
}
