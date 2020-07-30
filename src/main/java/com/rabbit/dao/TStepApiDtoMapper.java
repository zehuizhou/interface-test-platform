package com.rabbit.dao;

import com.rabbit.dto.StepApiDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TStepApiDtoMapper {
    List<StepApiDto> findDtoByTestcaseId(@Param("testcaseId") Long testcaseId);
}