package com.rabbit.dto;

import com.rabbit.model.Job;
import com.rabbit.model.JobParams;
import com.rabbit.model.TTestsuiteUi;
import lombok.Data;

import java.util.List;

@Data
public class JobDto extends Job {
    private static final long serialVersionUID = 1L;
    private List<TestsuiteUiDto> suiteList;
    private List<TPlanSuiteApiDto> apiSuiteList;
    private JobParams jobParams;//请求参数
}
