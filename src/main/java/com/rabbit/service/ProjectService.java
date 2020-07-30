package com.rabbit.service;
import com.github.pagehelper.PageInfo;
import com.rabbit.model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> selectAllByUserId(Long userId);

    int deleteByPrimaryKey(Long id);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Project record);

    List<Project> findAll();

    PageInfo<Project> findByAllwithPage(int page, int pageSize, Project project);

    List<Project> findByProjectName(String projectName);

    List<Project> findByProjectNameAndIdNot(String projectName, Long notId);

    List<Project> listByUserId(Long userId);

    int insert(Project record);

    int updateByPrimaryKeySelective(Project record);
}







