package com.rabbit.dao;

import com.rabbit.model.TApi;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface TApiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TApi record);

    int insertSelective(TApi record);

    TApi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TApi record);

    int updateByPrimaryKey(TApi record);

    List<TApi> findByAll(TApi tApi);

    List<TApi> findByProjectId(@Param("projectId") Long projectId);

    List<TApi> findByNameAndProjectId(@Param("name") String name, @Param("projectId") Long projectId);

    List<TApi> findByNameAndProjectIdAndIdNot(@Param("name") String name, @Param("projectId") Long projectId, @Param("notId") Long notId);

    List<TApi> findByApiSuiteId(@Param("apiSuiteId") Long apiSuiteId);

    int deleteByApiSuiteId(@Param("apiSuiteId") Long apiSuiteId);
}