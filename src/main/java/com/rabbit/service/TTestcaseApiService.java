package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.dto.TApiCaseResultDto;
import com.rabbit.dto.TestcaseApiDto;
import com.rabbit.model.TApiCaseResult;
import com.rabbit.model.TTestcaseApi;

import java.util.List;
import java.util.Map;

public interface TTestcaseApiService {


    int deleteByPrimaryKey(Long id);

    int insert(TTestcaseApi record);

    int insertSelective(TTestcaseApi record);

    TTestcaseApi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestcaseApi record);

    int updateByPrimaryKey(TTestcaseApi record);

    PageInfo<TTestcaseApi> findByAllwithPage(int page, int pageSize, TTestcaseApi tTestcaseApi);

    List<TTestcaseApi> findByCaseTypeAndProjectId(Integer caseType, Long projectId);

    void add(TestcaseApiDto testcaseApi);

    void edit(TestcaseApiDto testcaseApi);

    TestcaseApiDto selectDtoByPrimaryKey(Long id);

    TestcaseApiDto selectDtoByIdAndCaseType(Long id, Integer caseType);

    TApiCaseResultDto excCase(TestcaseApiDto testcaseApi);

    List<TTestcaseApi> findBySuiteId(Long id);

    List<TestcaseApiDto> selectDtoBySuiteId(Long id);
}




