package com.rabbit.service;

import com.rabbit.model.TApiResult;
import com.rabbit.model.TApi;
import com.rabbit.model.po.ApiParam;

import java.util.List;
import java.util.Map;

public interface RequestExecutorServer {
    TApiResult executeHttpRequest(TApi tApi, Map<String, Object> gVars, Map<String, Object> caseVars, List<ApiParam> params );
}






