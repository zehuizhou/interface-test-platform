package com.rabbit.service;

import com.rabbit.dto.StepApiDto;
import com.rabbit.model.TStepApi;

import java.util.List;

public interface TStepApiService {


    int deleteByPrimaryKey(Long id);

    int insert(TStepApi record);

    int insertSelective(TStepApi record);

    TStepApi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TStepApi record);

    int updateByPrimaryKey(TStepApi record);

    void insertApis(List<TStepApi> stepApiList);

    List<StepApiDto> findDtoByTestcaseId(Long testcaseId);

    void saveSteps(List<StepApiDto> testSteps);

    void copyStep(TStepApi testStep);

    List<TStepApi> findByTestcaseId(Long testcaseId);
}












