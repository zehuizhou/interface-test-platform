package com.rabbit.dto;

import com.rabbit.model.TTestsuiteUi;
import lombok.Data;

@Data
public class TestsuiteUiDto extends TTestsuiteUi {
	private static final long serialVersionUID = 1L;
	private Long jobId;
	private Long suiteCaseId;
	private Integer isValid;
	private Integer sort;//排序
}
