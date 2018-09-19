package com.buy.dao.pan;

import com.buy.entity.pan.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * pan user Mapper
 * @author yrp
 */
@Mapper
@Repository
public interface UserMapper {
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
}