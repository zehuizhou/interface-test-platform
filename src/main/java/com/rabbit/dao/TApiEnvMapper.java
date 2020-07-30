package com.rabbit.dao;

import com.rabbit.model.TApiEnv;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface TApiEnvMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TApiEnv record);

    int insertSelective(TApiEnv record);

    TApiEnv selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TApiEnv record);

    int updateByPrimaryKey(TApiEnv record);

    List<TApiEnv> findByAll(TApiEnv tApiEnv);

    List<TApiEnv> findByNameAndProjectId(@Param("name") String name, @Param("projectId") Long projectId);

    List<TApiEnv> findByNameAndProjectIdAndIdNot(@Param("name") String name, @Param("projectId") Long projectId, @Param("notId") Long notId);

    List<TApiEnv> findByProjectId(@Param("projectId") Long projectId);
}