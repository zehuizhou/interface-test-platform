package com.rabbit.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;

import com.rabbit.model.TTestPlanUiNewLog;

public interface TTestPlanUiNewLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTestPlanUiNewLog record);

    int insertSelective(TTestPlanUiNewLog record);

    TTestPlanUiNewLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestPlanUiNewLog record);

    int updateByPrimaryKey(TTestPlanUiNewLog record);

    List<TTestPlanUiNewLog> findByAll(TTestPlanUiNewLog tTestPlanUiNewLog);

}