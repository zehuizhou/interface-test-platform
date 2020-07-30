package com.rabbit.service;

import com.rabbit.model.TTestSuiteUiLog;

public interface TTestSuiteUiLogService {


    int deleteByPrimaryKey(Long id);

    int insert(TTestSuiteUiLog record);

    int insertSelective(TTestSuiteUiLog record);

    Long insertSelectiveReturnKey(TTestSuiteUiLog record);

    TTestSuiteUiLog insertSelectiveReturnObj(TTestSuiteUiLog record);

    TTestSuiteUiLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestSuiteUiLog record);

    int updateByPrimaryKey(TTestSuiteUiLog record);

    TTestSuiteUiLog findByPlanIdCount(Long planLogId);

}
