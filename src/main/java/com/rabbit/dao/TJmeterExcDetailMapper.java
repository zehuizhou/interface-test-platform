package com.rabbit.dao;

import com.rabbit.model.TJmeterExcDetail;

public interface TJmeterExcDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TJmeterExcDetail record);

    int insertSelective(TJmeterExcDetail record);

    TJmeterExcDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TJmeterExcDetail record);

    int updateByPrimaryKey(TJmeterExcDetail record);
}