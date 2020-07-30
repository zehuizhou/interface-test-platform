package com.rabbit.dto;

import com.rabbit.model.TApi;
import com.rabbit.model.TStepApi;
import com.rabbit.model.TTestcaseApi;
import lombok.Data;

import java.util.List;

@Data
public class StepApiDto extends TStepApi {
    private static final long serialVersionUID = 1L;
    private TApi api;
}
