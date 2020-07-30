package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.dto.TestcaseUiNewDto;
import com.rabbit.model.TTestcaseUiNew;

import java.util.List;

public interface TTestcaseUiNewService {


    int deleteByPrimaryKey(Long id);

    int insert(TTestcaseUiNew record);

    int insertSelective(TTestcaseUiNew record);

    TTestcaseUiNew selectByPrimaryKey(Long id);

    TestcaseUiNewDto selectDtoByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestcaseUiNew record);

    int updateByPrimaryKey(TTestcaseUiNew record);

    PageInfo<TTestcaseUiNew> findByAllwithPage(int page, int pageSize, TTestcaseUiNew tTestcaseUiNew);

    List<TTestcaseUiNew> findByNameAndProjectId(String name, Long projectId);

    List<TTestcaseUiNew> findByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId);

    TTestcaseUiNew add(TestcaseUiNewDto testcaseUiNewDto);

    TTestcaseUiNew edit(TestcaseUiNewDto testcaseUiNewDto);

    List<TestcaseUiNewDto> selectDtoBySuiteId(Long id);

    Long countBySuiteId(Long suiteId);

    TestcaseUiNewDto selectByDtoByPrimaryKeyAndCaseType(Long id, Integer caseType);

    List<TTestcaseUiNew> findByCaseTypeAndProjectId(Long caseType, Long projectId);

    TTestcaseUiNew copyCaseById(Long id);

    TTestcaseUiNew businesstoCase(Long id);
}








