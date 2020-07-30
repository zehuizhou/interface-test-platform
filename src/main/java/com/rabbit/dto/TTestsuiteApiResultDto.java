package com.rabbit.dto;

import com.rabbit.model.TApiCaseResult;
import com.rabbit.model.TTestsuiteApiResult;
import lombok.Data;

import java.util.List;

@Data
public class TTestsuiteApiResultDto extends TTestsuiteApiResult {
    private static final long serialVersionUID = 1L;
    private List<TApiCaseResult> caseResults;
}
