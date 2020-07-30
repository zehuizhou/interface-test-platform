package com.rabbit.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ApiCaseResult implements Serializable {
    private int resultType;

    private List<TApiResult> rspAsserts;
    private Long rspTime;
    private String exception;
    private static final long serialVersionUID = 1L;
}