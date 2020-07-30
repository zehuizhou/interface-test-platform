package com.rabbit.dao;

import com.rabbit.dto.TestcaseUiNewLogDto;
import org.apache.ibatis.annotations.Param;

import com.rabbit.model.TTestCaseUiNewLog;

import java.util.List;

public interface TTestCaseUiNewLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTestCaseUiNewLog record);

    int insertSelective(TTestCaseUiNewLog record);

    TTestCaseUiNewLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestCaseUiNewLog record);

    int updateByPrimaryKey(TTestCaseUiNewLog record);

    List<TTestCaseUiNewLog> findByAll(TTestCaseUiNewLog tTestCaseUiNewLog);

    int deleteByPlanLogId(@Param("planLogId") Long planLogId);

    Long countBySuiteLogIdAndStatus(@Param("suiteLogId") Long suiteLogId, @Param("status") Integer status);

    List<TestcaseUiNewLogDto> findDtoByPlanLogId(@Param("planLogId") Long planLogId);

}