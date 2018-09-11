package com.buy.service;

import com.buy.entity.pan.User;

import java.util.List;

public interface UserService {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    List<com.buy.entity.test.User> selectTestAll();
}
