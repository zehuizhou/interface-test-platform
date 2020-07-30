package com.rabbit.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class TSuiteCaseApi implements Serializable {
    private Long id;

    private Long suiteId;

    private Long caseId;

    private Integer sort;

    /**
    * 1:有效，2：无效
    */
    private Integer isValid;

    private static final long serialVersionUID = 1L;
}