package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.TTestStepUiNewLog;

public interface TTestStepUiNewLogService {


    int deleteByPrimaryKey(Long id);

    int insert(TTestStepUiNewLog record);

    int insertSelective(TTestStepUiNewLog record);

    TTestStepUiNewLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestStepUiNewLog record);

    int updateByPrimaryKey(TTestStepUiNewLog record);

    PageInfo<TTestStepUiNewLog> findByAllwithPage(int page, int pageSize, TTestStepUiNewLog tTestStepUiNewLog);

    String getReportHtml(Long logId,String language);
}
