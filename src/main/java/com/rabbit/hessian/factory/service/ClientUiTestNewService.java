package com.rabbit.hessian.factory.service;

import com.rabbit.model.Job;
import com.rabbit.model.Result;

public interface ClientUiTestNewService {
    Result runPlan(Job job,Long planLogId);
}
