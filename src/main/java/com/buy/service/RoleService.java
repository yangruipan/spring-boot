package com.buy.service;

import com.buy.entity.Role;

import java.util.List;

public interface RoleService {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
}
