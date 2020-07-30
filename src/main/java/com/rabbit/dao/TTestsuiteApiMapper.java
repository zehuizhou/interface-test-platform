package com.rabbit.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Date;

import com.rabbit.model.TTestsuiteApi;

public interface TTestsuiteApiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTestsuiteApi record);

    int insertSelective(TTestsuiteApi record);

    TTestsuiteApi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestsuiteApi record);

    int updateByPrimaryKey(TTestsuiteApi record);

    List<TTestsuiteApi> findByAll(TTestsuiteApi tTestsuiteApi);

    List<TTestsuiteApi> findByProjectId(@Param("projectId") Long projectId);

    List<TTestsuiteApi> selectByNameAndProjectId(@Param("name") String name, @Param("projectId") Long projectId);

    List<TTestsuiteApi> selectByNameAndProjectIdAndIdNot(@Param("name") String name, @Param("projectId") Long projectId, @Param("notId") Long notId);


}