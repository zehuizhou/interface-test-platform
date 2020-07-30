package com.rabbit.dao;

import com.rabbit.model.TApiCaseResult;import org.apache.ibatis.annotations.Param;import java.util.List;

public interface TApiCaseResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TApiCaseResult record);

    int insertSelective(TApiCaseResult record);

    TApiCaseResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TApiCaseResult record);

    int updateByPrimaryKey(TApiCaseResult record);

    int deleteByPlanLogId(@Param("planLogId") Long planLogId);

    List<TApiCaseResult> findByAll(TApiCaseResult tApiCaseResult);

    List<TApiCaseResult> findBySuiteResultId(@Param("suiteResultId")Long suiteResultId);


}