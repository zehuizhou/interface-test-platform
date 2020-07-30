package com.rabbit.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.rabbit.model.TPlanSuiteUi;

public interface TPlanSuiteUiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TPlanSuiteUi record);

    int insertSelective(TPlanSuiteUi record);

    TPlanSuiteUi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPlanSuiteUi record);

    int updateByPrimaryKey(TPlanSuiteUi record);

    List<TPlanSuiteUi> findByJobId(@Param("jobId") Long jobId);

    int deleteByJobId(@Param("jobId") Long jobId);

    Long countByJobId(@Param("jobId") Long jobId);
}