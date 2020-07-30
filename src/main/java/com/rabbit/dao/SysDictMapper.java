package com.rabbit.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.rabbit.model.SysDict;

public interface SysDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    SysDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);

    List<SysDict> findAll();
    List<SysDict> findByKey(@Param("key")String key);

}