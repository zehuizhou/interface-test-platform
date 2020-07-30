package com.rabbit.dao;
import org.apache.ibatis.annotations.Param;

import com.rabbit.model.TSuiteCaseApi;

public interface TSuiteCaseApiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TSuiteCaseApi record);

    int insertSelective(TSuiteCaseApi record);

    TSuiteCaseApi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TSuiteCaseApi record);

    int updateByPrimaryKey(TSuiteCaseApi record);

    Integer findMaxSortBySuiteId(@Param("suiteId")Long suiteId);

    int deleteBySuiteId(@Param("suiteId")Long suiteId);


}