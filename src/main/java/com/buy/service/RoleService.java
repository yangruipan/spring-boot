package com.buy.service;

import com.buy.entity.pan.Role;

import java.util.List;

/**
 * roleService
 * @author yrp
 */
public interface RoleService {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(Role record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<Role> selectAll();

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Role record);
}
