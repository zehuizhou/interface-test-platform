package com.rabbit.dao;

import org.apache.ibatis.annotations.Param;

import com.rabbit.model.TApiResult;

import java.util.List;

public interface TApiResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TApiResult record);

    int insertSelective(TApiResult record);

    TApiResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TApiResult record);

    int updateByPrimaryKey(TApiResult record);

    int deleteByPlanLogId(@Param("planLogId") Long planLogId);

    List<TApiResult> findByAll(TApiResult tApiResult);


}