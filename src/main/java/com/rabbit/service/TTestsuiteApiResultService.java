package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.dto.TTestsuiteApiResultDto;
import com.rabbit.model.TTestSuiteUiLog;
import com.rabbit.model.TTestsuiteApiResult;

import java.util.List;

public interface TTestsuiteApiResultService {

    int insert(TTestsuiteApiResult record);

    int insertSelective(TTestsuiteApiResult record);

    int updateByPrimaryKeySelective(TTestsuiteApiResult record);

    int updateByPrimaryKey(TTestsuiteApiResult record);

    int deleteByPrimaryKey(Long id);

    TTestsuiteApiResult selectByPrimaryKey(Long id);

    TTestsuiteApiResult findByPlanIdCount(Long planLogId);
    List<TTestsuiteApiResultDto> findDtoByAll(TTestsuiteApiResult tTestsuiteApiResult);
}


