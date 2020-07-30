package com.rabbit.dto;

import com.rabbit.model.PageElement;
import lombok.Data;

@Data
public class PageElementDto extends PageElement {
    private static final long serialVersionUID = 1L;
    private Long projectId;
    /**
     * 页面名称
     */
    private String pageName;
}
