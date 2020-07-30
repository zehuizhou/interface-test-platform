package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.TTestPlanResultApi;

public interface TTestPlanResultApiService {



    int insert(TTestPlanResultApi record);

    int insertSelective(TTestPlanResultApi record);


    int updateByPrimaryKeySelective(TTestPlanResultApi record);

    int updateByPrimaryKey(TTestPlanResultApi record);

    int deleteByPrimaryKey(Long id);

    TTestPlanResultApi selectByPrimaryKey(Long id);
    PageInfo<TTestPlanResultApi> findByAllwithPage(int page, int pageSize, TTestPlanResultApi tTestPlanResultApi);
}


