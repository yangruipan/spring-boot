package com.buy.dao.pan;

import com.buy.entity.pan.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 角色mapper
 * @author yrp
 */
@Mapper
@Repository
public interface RoleMapper {
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