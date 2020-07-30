package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.TTestInterface;

import java.util.List;

public interface TTestInterfaceService {


    int deleteByPrimaryKey(Long id);

    int insert(TTestInterface record);

    int insertSelective(TTestInterface record);

    TTestInterface selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestInterface record);

    int updateByPrimaryKey(TTestInterface record);

    PageInfo<TTestInterface> findByAllwithPage(int page, int pageSize, TTestInterface tTestInterface);

    List<TTestInterface> findByProjectId(Long projectId);

    List<TTestInterface> findByNameAndProjectId(String name, Long projectId);

    List<TTestInterface> findByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId);

}

