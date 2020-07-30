package com.rabbit.dao;

import java.util.List;
import java.util.Date;

import com.rabbit.dto.TTestsuiteApiResultDto;
import com.rabbit.model.TTestSuiteUiLog;
import com.rabbit.model.TTestsuiteApiResult;
import org.apache.ibatis.annotations.Param;

public interface TTestsuiteApiResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTestsuiteApiResult record);

    int insertSelective(TTestsuiteApiResult record);

    TTestsuiteApiResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestsuiteApiResult record);

    int updateByPrimaryKey(TTestsuiteApiResult record);

    int deleteByPlanLogId(@Param("planLogId") Long planLogId);

    List<TTestsuiteApiResult> findByAll(TTestsuiteApiResult tTestsuiteApiResult);

    List<TTestsuiteApiResultDto> findDtoByAll(TTestsuiteApiResult tTestsuiteApiResult);

    TTestsuiteApiResult findByPlanIdCount(@Param("planId") Long planLogId);

}