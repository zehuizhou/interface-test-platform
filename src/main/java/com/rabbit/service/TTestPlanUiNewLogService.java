package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.TTestPlanUiNewLog;

public interface TTestPlanUiNewLogService {


    int deleteByPrimaryKey(Long id);

    int insert(TTestPlanUiNewLog record);

    int insertSelective(TTestPlanUiNewLog record);

    TTestPlanUiNewLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestPlanUiNewLog record);

    int updateByPrimaryKey(TTestPlanUiNewLog record);

    PageInfo<TTestPlanUiNewLog> findByAllwithPage(int page, int pageSize, TTestPlanUiNewLog tTestPlanUiNewLog);

}

