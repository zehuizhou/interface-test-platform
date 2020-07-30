package com.rabbit.dao;

import com.rabbit.dto.TestcaseUiNewDto;
import com.rabbit.model.TTestcaseUiNew;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TTestcaseUiNewDtoMapper {
    TestcaseUiNewDto selectDtoByPrimaryKey(Long id);

    TestcaseUiNewDto selectByDtoByPrimaryKeyAndCaseType(@Param("id") Long id, @Param("caseType") Integer caseType);

    List<TestcaseUiNewDto> selectDtoBySuiteId(Long id);
}