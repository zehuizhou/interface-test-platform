package com.rabbit.dao;
import com.rabbit.dto.TApiSuiteDto;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.rabbit.model.TApiSuite;

public interface TApiSuiteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TApiSuite record);

    int insertSelective(TApiSuite record);

    TApiSuite selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TApiSuite record);

    int updateByPrimaryKey(TApiSuite record);

    List<TApiSuiteDto> findByProjectId(@Param("projectId")Long projectId);

    List<TApiSuite> findByNameAndProjectId(@Param("name")String name,@Param("projectId")Long projectId);

    List<TApiSuite> findByNameAndProjectIdAndIdNot(@Param("name")String name,@Param("projectId")Long projectId,@Param("notId")Long notId);

}