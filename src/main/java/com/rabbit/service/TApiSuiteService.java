package com.rabbit.service;

import com.rabbit.dto.TApiSuiteDto;
import com.rabbit.model.TApiResult;
import com.rabbit.model.TApi;
import com.rabbit.model.TApiSuite;

import java.util.List;
import java.util.Map;

public interface TApiSuiteService {


    int deleteByPrimaryKey(Long id);

    int insert(TApiSuite record);

    int insertSelective(TApiSuite record);

    TApiSuite selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TApiSuite record);

    int updateByPrimaryKey(TApiSuite record);

    List<TApiSuiteDto> findDtoByProjectId(Long projectId);

    List<TApiSuite> findByNameAndProjectId(String name, Long projectId);

    List<TApiSuite> findByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId);

    List<TApiSuiteDto> findByProjectId(Long projectId);
}
