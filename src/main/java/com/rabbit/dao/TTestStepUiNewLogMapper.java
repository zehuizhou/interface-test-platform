package com.rabbit.dao;
import java.util.List;
import java.util.Date;
import org.apache.ibatis.annotations.Param;

import com.rabbit.model.TTestStepUiNewLog;

public interface TTestStepUiNewLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTestStepUiNewLog record);

    int insertSelective(TTestStepUiNewLog record);

    TTestStepUiNewLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestStepUiNewLog record);

    int updateByPrimaryKey(TTestStepUiNewLog record);

    int deleteByPlanJobId(@Param("planJobId")Long planJobId);

    List<TTestStepUiNewLog> findByAll(TTestStepUiNewLog tTestStepUiNewLog);

    List<TTestStepUiNewLog> findByPlanJobId(@Param("planJobId")Long planJobId);

    List<TTestStepUiNewLog> findByCaseLogId(@Param("caseLogId")Integer caseLogId);
}