package com.rabbit.service;

import com.rabbit.dto.JobDto;
import com.rabbit.model.TPlanSuiteApi;

public interface TPlanSuiteApiService {


    int deleteByPrimaryKey(Long id);

    int insert(TPlanSuiteApi record);

    int insertSelective(TPlanSuiteApi record);

    TPlanSuiteApi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPlanSuiteApi record);

    int updateByPrimaryKey(TPlanSuiteApi record);

    int deleteByJobId(Long jobId);

    void addApiSuiteToPlan(JobDto job);
}

