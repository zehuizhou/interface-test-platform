package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.TApiResult;

import java.util.List;

public interface TApiResultService {


    int deleteByPrimaryKey(Long id);

    int insert(TApiResult record);

    int insertSelective(TApiResult record);

    TApiResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TApiResult record);

    int updateByPrimaryKey(TApiResult record);

    List<TApiResult> findByAll(TApiResult tApiResult);
}





