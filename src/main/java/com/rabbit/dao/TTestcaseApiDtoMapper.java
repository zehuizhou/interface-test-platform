package com.rabbit.dao;

import com.rabbit.dto.TestcaseApiDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TTestcaseApiDtoMapper {
    TestcaseApiDto selectDtoByPrimaryKey(Long id);

    TestcaseApiDto selectDtoByIdAndCaseType(@Param("id") Long id, @Param("caseType") Integer caseType);

    List<TestcaseApiDto> selectDtoBySuiteId(Long id);
}