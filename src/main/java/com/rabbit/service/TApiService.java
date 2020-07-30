package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.Result;
import com.rabbit.model.TApi;
import com.rabbit.model.TApiResult;
import com.rabbit.model.po.Action;
import com.rabbit.model.po.ApiParam;

import java.util.List;
import java.util.Map;

public interface TApiService {


    int deleteByPrimaryKey(Long id);

    int insert(TApi record);

    int insertSelective(TApi record);

    TApi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TApi record);

    int updateByPrimaryKey(TApi record);

    PageInfo<TApi> findByAllwithPage(int page, int pageSize, TApi tApi);

    List<TApi> findByProjectId(Long projectId);

    List<TApi> findByNameAndProjectId(String name, Long projectId);

    List<TApi> findByNameAndProjectIdAndIdNot(String name, Long projectId, Long notId);

    Result runAction(Action action, Map<String, Object> gVars, Map<String, Object> caseVars);

    TApiResult excApi(TApi api, Map<String, Object> gVars, Map<String, Object> caseVars,List<ApiParam> params);
}












