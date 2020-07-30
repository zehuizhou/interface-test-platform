package com.rabbit.service;

import com.rabbit.model.TSuiteUi;

import java.util.List;

public interface TSuiteUiService {


    int deleteByPrimaryKey(Long id);

    int insert(TSuiteUi record);

    TSuiteUi selectByPrimaryKey(Long id);

    int updateByPrimaryKey(TSuiteUi record);

    List<TSuiteUi> listTreeByProjectId(Long projectId);
}



