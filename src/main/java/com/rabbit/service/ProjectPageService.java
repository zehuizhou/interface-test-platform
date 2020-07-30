package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.dto.ProjectPageDto;
import com.rabbit.model.ProjectPage;

import java.util.List;

public interface ProjectPageService {

    int deleteByPrimaryKey(Long id);

    int insertSelective(ProjectPage record);

    int updateByPrimaryKeySelective(ProjectPage record);

    int updateByPrimaryKey(ProjectPage record);

    ProjectPageDto selectDtoByPrimaryKey(Long id);

    List<ProjectPageDto> findDtoByProjectId(Long projectId);

    List<ProjectPageDto> findDtoByProjectIdAndPageName(Long projectId, String pageName);

    List<ProjectPageDto> findDtoByProjectIdAndPageNameAndIdNot(Long projectId, String pageName, Long notId);

    PageInfo<ProjectPageDto> findDtoByAllwithPage(int page, int pageSize, ProjectPage projectPage);

    void copyPageById(Long id) ;
}


