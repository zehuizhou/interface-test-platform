package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.TTestsuiteApi;

import java.util.List;

public interface TTestsuiteApiService{


    int deleteByPrimaryKey(Long id);

    int insert(TTestsuiteApi record);

    int insertSelective(TTestsuiteApi record);

    TTestsuiteApi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestsuiteApi record);

    int updateByPrimaryKey(TTestsuiteApi record);

    PageInfo<TTestsuiteApi> findByAllwithPage(int page, int pageSize, TTestsuiteApi tTestsuiteApi);

    List<TTestsuiteApi> findByProjectId(Long projectId);

    List<TTestsuiteApi> selectByNameAndProjectId(String name, Long projectId);

    List<TTestsuiteApi> selectByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId);
}
