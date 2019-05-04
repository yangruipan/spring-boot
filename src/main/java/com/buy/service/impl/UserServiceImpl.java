package com.buy.service.impl;

import com.buy.dao.pan.UserMapper;
import com.buy.entity.pan.User;
import com.buy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * user service
 * @author yrp
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private com.buy.dao.test.TestUserMapper userTestMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 测试事务控制
     * @param record
     * @return
     */
    @Transactional
    @Override
    public int insert(User record) {

        User user = new User();
        user.setAge(2);
        user.setSex(1);
        user.setName("张三");
        user.setCreateTime(new Date());
        userMapper.insert(user);

        int number = 6/0;

        record.setAge(3);
        record.setSex(2);
        record.setName("李四");
        record.setCreateTime(new Date());

        return userMapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<com.buy.entity.test.User> selectTestAll() {
        return userTestMapper.selectAll();
    }
}
