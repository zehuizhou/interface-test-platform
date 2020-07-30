package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.dto.TestcaseUiNewDto;
import com.rabbit.model.TTestCaseUiNewLog;

public interface TTestCaseUiNewLogService {


    int deleteByPrimaryKey(Long id);

    int insert(TTestCaseUiNewLog record);

    int insertSelective(TTestCaseUiNewLog record);

    Long insertSelectiveReturnKey(TTestCaseUiNewLog record);

    TTestCaseUiNewLog insertSelectiveReturnObj(TTestCaseUiNewLog record);

    TTestCaseUiNewLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestCaseUiNewLog record);

    int updateByPrimaryKey(TTestCaseUiNewLog record);

    PageInfo<TTestCaseUiNewLog> findByAllwithPage(int page, int pageSize, TTestCaseUiNewLog tTestCaseUiNewLog);

    Long countBySuiteLogIdAndStatus(Long suiteLogId, Integer status);

}

