package com.rabbit.service;

import com.rabbit.model.TJmeterExcDetail;

public interface TJmeterExcDetailService {


    int deleteByPrimaryKey(Long id);

    int insert(TJmeterExcDetail record);

    int insertSelective(TJmeterExcDetail record);

    TJmeterExcDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TJmeterExcDetail record);

    int updateByPrimaryKey(TJmeterExcDetail record);

}


