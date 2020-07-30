package com.rabbit.service;

import com.rabbit.dto.StepUiNewDto;
import com.rabbit.model.TStepUiNew;

import java.util.List;

public interface TStepUiNewService {


    int deleteByPrimaryKey(Long id);

    int insert(TStepUiNew record);

    int insertSelective(TStepUiNew record);

    TStepUiNew selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TStepUiNew record);

    int updateByPrimaryKey(TStepUiNew record);

    String savaStep(List<TStepUiNew> uiTestStepList);


    List<StepUiNewDto> findDtoByTestcaseId(Long testcaseId);

}

