package com.rabbit.dao;

import com.rabbit.model.GlobalParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GlobalParamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GlobalParam record);

    int insertSelective(GlobalParam record);

    GlobalParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GlobalParam record);

    int updateByPrimaryKey(GlobalParam record);

    List<GlobalParam> findByAll(GlobalParam globalParam);

    List<GlobalParam> findByParamNameAndProjectIdAndType(@Param("paramName") String paramName, @Param("projectId") Long projectId, @Param("type") Integer type);

    List<GlobalParam> findByParamNameAndProjectIdAndTypeAndIdNot(@Param("paramName") String paramName, @Param("projectId") Long projectId, @Param("type") Integer type, @Param("notId") Long notId);

    List<GlobalParam> findByProjectIdAndType(@Param("projectId") Long projectId, @Param("type") Integer type);
}