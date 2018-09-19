package com.buy.service;

import com.buy.entity.pan.User;

import java.util.List;

/**
 * user service interface
 * @author yrp
 */
public interface UserService {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<User> selectAll();

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 查询test数据库所有用户信息
     * @return
     */
    List<com.buy.entity.test.User> selectTestAll();
}
