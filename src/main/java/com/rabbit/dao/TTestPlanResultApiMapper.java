package com.rabbit.dao;

import java.util.List;
import java.util.Date;

import com.rabbit.model.TTestPlanResultApi;
import org.apache.ibatis.annotations.Param;

public interface TTestPlanResultApiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTestPlanResultApi record);

    int insertSelective(TTestPlanResultApi record);

    TTestPlanResultApi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestPlanResultApi record);

    int updateByPrimaryKey(TTestPlanResultApi record);

    List<TTestPlanResultApi> findByAll(TTestPlanResultApi tTestPlanResultApi);


}