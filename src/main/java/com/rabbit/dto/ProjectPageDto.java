package com.rabbit.dto;

import com.rabbit.model.PageElement;
import com.rabbit.model.ProjectPage;
import lombok.Data;

import java.util.List;


@Data
public class ProjectPageDto extends ProjectPage {
    private String projectName;
    private List<PageElement> pageElementList;
}
