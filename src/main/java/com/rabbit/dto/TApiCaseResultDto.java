package com.rabbit.dto;

import com.rabbit.model.TApiCaseResult;
import com.rabbit.model.TApiResult;
import com.rabbit.model.TTestsuiteUi;
import lombok.Data;

import java.util.List;

@Data
public class TApiCaseResultDto extends TApiCaseResult {
    private static final long serialVersionUID = 1L;
    private List<TApiResult> steps;
}
