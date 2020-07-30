package com.rabbit.dao;

import com.rabbit.model.TSuiteUi;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSuiteUiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TSuiteUi record);

    int insertOrUpdate(TSuiteUi record);

    int insertOrUpdateSelective(TSuiteUi record);

    int insertSelective(TSuiteUi record);

    TSuiteUi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TSuiteUi record);

    int updateByPrimaryKey(TSuiteUi record);

    int updateBatch(List<TSuiteUi> list);

    int batchInsert(@Param("list") List<TSuiteUi> list);

    List<TSuiteUi> findByProjectId(@Param("projectId")Long projectId);

}