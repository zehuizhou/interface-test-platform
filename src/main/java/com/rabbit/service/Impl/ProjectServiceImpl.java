package com.rabbit.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.dao.ProjectMapper;
import com.rabbit.model.Project;
import com.rabbit.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        projectMapper.deleteUserProjectByProjectId(id);
        return projectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Project record) {
        return projectMapper.insertSelective(record);
    }

    @Override
    public Project selectByPrimaryKey(Long id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Project record) {
        return projectMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Project> findAll() {
        return projectMapper.findAll();
    }

    @Override
    public PageInfo<Project> findByAllwithPage(int page, int pageSize, Project project) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(projectMapper.findByAll(project));
    }

    @Override
    public List<Project> findByProjectName(String projectName) {
        return projectMapper.findByProjectName(projectName);
    }

    @Override
    public List<Project> findByProjectNameAndIdNot(String projectName, Long notId) {
        return projectMapper.findByProjectNameAndIdNot(projectName, notId);
    }

    @Override
    public List<Project> selectAllByUserId(Long userId) {
        return projectMapper.selectAllByUserId(userId);
    }

    @Override
    public List<Project> listByUserId(Long userId) {
        return projectMapper.listByUserId(userId);
    }

    @Override
    public int insert(Project record) {
        return projectMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Project record) {
        return projectMapper.updateByPrimaryKeySelective(record);
    }
}







