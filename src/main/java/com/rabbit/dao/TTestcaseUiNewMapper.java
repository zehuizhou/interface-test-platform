package com.rabbit.dao;

import com.rabbit.model.TTestcaseUiNew;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface TTestcaseUiNewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTestcaseUiNew record);

    int insertSelective(TTestcaseUiNew record);

    TTestcaseUiNew selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestcaseUiNew record);

    int updateByPrimaryKey(TTestcaseUiNew record);

    List<TTestcaseUiNew> findByAll(TTestcaseUiNew tTestcaseUiNew);

    List<TTestcaseUiNew> findByCaseTypeAndProjectId(@Param("caseType") Long caseType, @Param("projectId") Long projectId);

    List<TTestcaseUiNew> findByNameAndProjectId(@Param("name") String name, @Param("projectId") Long projectId);

    List<TTestcaseUiNew> findByNameAndProjectIdAndIdNot(@Param("name") String name, @Param("projectId") Long projectId, @Param("notId") Long notId);

    List<TTestcaseUiNew> findByProjectIdAndCaseTypeAndName(@Param("projectId")Long projectId,@Param("caseType")Long caseType,@Param("name")String name);


}