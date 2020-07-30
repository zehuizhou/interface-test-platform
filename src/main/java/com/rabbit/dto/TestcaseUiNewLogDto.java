package com.rabbit.dto;

import com.rabbit.model.TTestCaseUiNewLog;
import com.rabbit.model.TTestStepUiNewLog;
import lombok.Data;

import java.util.List;

@Data
public class TestcaseUiNewLogDto extends TTestCaseUiNewLog {
    private List<TTestStepUiNewLog> testStepLogs;
}
