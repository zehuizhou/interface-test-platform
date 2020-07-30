package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.dto.JobDto;
import com.rabbit.dto.TestsuiteUiDto;
import com.rabbit.model.TSuiteCaseUi;
import com.rabbit.model.TTestsuiteUi;

import java.util.List;

public interface TTestsuiteUiService {


    int deleteByPrimaryKey(Long id);

    int insert(TTestsuiteUi record);

    int insertSelective(TTestsuiteUi record);

    TTestsuiteUi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestsuiteUi record);

    int updateByPrimaryKey(TTestsuiteUi record);

    PageInfo<TTestsuiteUi> findByAllwithPage(int page, int pageSize, TTestsuiteUi tTestsuiteUi);

    List<TTestsuiteUi> selectByNameAndProjectId(String name, Long projectId);

    List<TTestsuiteUi> selectByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId);

    List<TTestsuiteUi> findByProjectId(Long projectId);

   void updateCaseSort(List<TSuiteCaseUi> suiteCaseUis);

    void addCaseToSuite(List<TSuiteCaseUi> suiteCaseUis);

   int deleteSuiteCaseById(Long id);

   void  addUiSuiteToPlan(JobDto jobDto);

    List<TestsuiteUiDto> findDtoByJobId(Long jobId);
}

