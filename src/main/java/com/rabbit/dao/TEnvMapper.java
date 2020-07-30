package com.rabbit.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Date;

import com.rabbit.model.TEnv;

public interface TEnvMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TEnv record);

    int insertSelective(TEnv record);

    TEnv selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TEnv record);

    int updateByPrimaryKey(TEnv record);

    List<TEnv> findByAll(TEnv tEnv);

    List<TEnv> findByProjectId(@Param("projectId") Long projectId);

    List<TEnv> findByNameAndProjectId(@Param("name") String name, @Param("projectId") Long projectId);

    List<TEnv> findByNameAndProjectIdAndIdNot(@Param("name") String name, @Param("projectId") Long projectId, @Param("notId") Long notId);
}