package com.rabbit.dto;

import com.rabbit.model.TApi;
import com.rabbit.model.TApiSuite;
import lombok.Data;

import java.util.List;

@Data
public class TApiSuiteDto extends TApiSuite {
	private static final long serialVersionUID = 1L;
	private List<TApi> apiList;
}
