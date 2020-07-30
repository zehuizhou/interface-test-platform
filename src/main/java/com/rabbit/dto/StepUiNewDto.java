package com.rabbit.dto;

import com.rabbit.model.TStepUiNew;
import lombok.Data;

@Data
public class StepUiNewDto extends TStepUiNew {
	private String byType;
	private String byValue;//排序
}
