package com.rabbit.dao;

import com.rabbit.model.TTestSuiteUiLog;
import org.apache.ibatis.annotations.Param;

public interface TTestSuiteUiLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTestSuiteUiLog record);

    int insertSelective(TTestSuiteUiLog record);

    TTestSuiteUiLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestSuiteUiLog record);

    int updateByPrimaryKey(TTestSuiteUiLog record);

    TTestSuiteUiLog findByPlanIdCount(@Param("planId")Long planLogId);

    int deleteByPlanLogId(@Param("planLogId")Long planLogId);

    Long countByPlanLogIdAndStatus(@Param("planLogId")Long planLogId,@Param("status")Integer status);

}