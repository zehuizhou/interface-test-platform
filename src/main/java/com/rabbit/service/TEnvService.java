package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.TEnv;

import java.util.List;

public interface TEnvService {


    int deleteByPrimaryKey(Long id);

    int insert(TEnv record);

    int insertSelective(TEnv record);

    TEnv selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TEnv record);

    int updateByPrimaryKey(TEnv record);

    PageInfo<TEnv> findByAllwithPage(int page, int pageSize, TEnv tEnv);

    List<TEnv> findByProjectId(Long projectId);

    List<TEnv> findByNameAndProjectId(String name, Long projectId);

    List<TEnv> findByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId);
}
