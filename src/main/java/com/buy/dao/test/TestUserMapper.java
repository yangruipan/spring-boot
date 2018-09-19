package com.buy.dao.test;

import com.buy.entity.test.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * testUser Mapper
 * @author yrp
 */
@Mapper
@Repository
public interface TestUserMapper {
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
}