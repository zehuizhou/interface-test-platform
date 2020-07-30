package com.rabbit.dto;

import com.rabbit.model.TApi;
import lombok.Data;

@Data
public class ApiDto extends TApi {
    private static final long serialVersionUID = 1L;
    private String domain;
    private String prepend;
    private String global;
}
