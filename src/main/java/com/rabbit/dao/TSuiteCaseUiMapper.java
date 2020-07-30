package com.rabbit.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.rabbit.model.TSuiteCaseUi;

public interface TSuiteCaseUiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TSuiteCaseUi record);

    int insertSelective(TSuiteCaseUi record);

    TSuiteCaseUi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TSuiteCaseUi record);

    int updateByPrimaryKey(TSuiteCaseUi record);

    Integer findMaxSortBySuiteId(@Param("suiteId")Long suiteId);

    int deleteByCaseId(@Param("caseId")Long caseId);

    int deleteBySuiteId(@Param("suiteId")Long suiteId);

    Long countBySuiteId(@Param("suiteId")Long suiteId);

    List<TSuiteCaseUi> findByCaseId(@Param("caseId")Long caseId);



}