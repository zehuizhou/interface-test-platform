package com.rabbit.dao;

import com.rabbit.dto.ProjectPageDto;
import com.rabbit.model.ProjectPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectPageDtoMapper {
    ProjectPageDto selectDtoByPrimaryKey(Long id);

    List<ProjectPageDto> findDtoByAll(ProjectPage projectPage);

    List<ProjectPageDto> findDtoByProjectId(@Param("projectId") Long projectId);

    List<ProjectPageDto> findDtoByProjectIdAndPageName(@Param("projectId") Long projectId, @Param("pageName") String pageName);

    List<ProjectPageDto> findDtoByProjectIdAndPageNameAndIdNot(@Param("projectId") Long projectId, @Param("pageName") String pageName, @Param("notId") Long notId);
}