package com.rabbit.dto;

import com.rabbit.model.TStepUiNew;
import com.rabbit.model.TTestcaseUiNew;
import lombok.Data;

import java.util.List;

@Data
public class TestcaseUiNewDto extends TTestcaseUiNew {
    private static final long serialVersionUID = 1L;
    //	private String projectName;
    private Long suiteId;
    private Long suiteCaseId;
    private List<TStepUiNew> testSteps;
    private Integer sort;//排序
}
