package com.rabbit.service;

import com.rabbit.model.TSuiteCaseApi;
import com.rabbit.model.TSuiteCaseUi;

import java.util.List;

public interface TSuiteCaseApiService{


    int deleteByPrimaryKey(Long id);

    int insert(TSuiteCaseApi record);

    int insertSelective(TSuiteCaseApi record);

    TSuiteCaseApi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TSuiteCaseApi record);

    int updateByPrimaryKey(TSuiteCaseApi record);

    void updateCaseSort(List<TSuiteCaseApi> suiteCaseUis);

    void addCaseToSuite(List<TSuiteCaseApi> suiteCaseUis);
}
