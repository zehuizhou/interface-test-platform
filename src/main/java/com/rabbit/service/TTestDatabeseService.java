package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.TTestDatabese;

import java.util.List;

public interface TTestDatabeseService{


    int deleteByPrimaryKey(Integer id);

    int insert(TTestDatabese record);

    int insertSelective(TTestDatabese record);

    TTestDatabese selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTestDatabese record);

    int updateByPrimaryKey(TTestDatabese record);

    PageInfo<TTestDatabese> findByAllwithPage(int page, int pageSize, TTestDatabese tTestDatabese);

    List<TTestDatabese> findByProjectId(Long projectId);

    List<TTestDatabese> findByNameAndProjectId(String name, Long projectId);

    List<TTestDatabese> findByNameAndProjectIdAndIdNot(String name, Long projectId, Integer notId);

}
