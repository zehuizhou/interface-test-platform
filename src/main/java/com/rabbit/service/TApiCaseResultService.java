package com.rabbit.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.TApiCaseResult;

public interface TApiCaseResultService {


    int insert(TApiCaseResult record);

    int insertSelective(TApiCaseResult record);

    int updateByPrimaryKeySelective(TApiCaseResult record);

    int updateByPrimaryKey(TApiCaseResult record);

    int deleteByPrimaryKey(Long id);

    TApiCaseResult selectByPrimaryKey(Long id);
    List<TApiCaseResult> findByAll(TApiCaseResult tApiCaseResult);
}





