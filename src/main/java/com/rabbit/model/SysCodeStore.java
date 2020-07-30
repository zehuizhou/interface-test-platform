package com.rabbit.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class SysCodeStore implements Serializable {
    private Long id;

    private String code;

    private static final long serialVersionUID = 1L;
}