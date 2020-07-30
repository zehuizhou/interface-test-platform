package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.TApiEnv;

import java.util.List;

public interface TApiEnvService {


    int deleteByPrimaryKey(Long id);

    int insert(TApiEnv record);

    int insertSelective(TApiEnv record);

    TApiEnv selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TApiEnv record);

    int updateByPrimaryKey(TApiEnv record);

    PageInfo<TApiEnv> findByAllwithPage(int page, int pageSize, TApiEnv tApiEnv);

    List<TApiEnv> findByNameAndProjectId(String name, Long projectId);

    List<TApiEnv> findByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId);

    List<TApiEnv> findByProjectId(Long projectId);
}








