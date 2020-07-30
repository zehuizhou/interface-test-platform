package com.rabbit.dto;

import com.rabbit.model.TPlanSuiteApi;
import com.rabbit.model.TTestsuiteUi;
import lombok.Data;

@Data
public class TPlanSuiteApiDto extends TPlanSuiteApi {
	private static final long serialVersionUID = 1L;
	private String suiteName;
}
