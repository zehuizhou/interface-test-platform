package com.rabbit.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.dao.PageElementMapper;
import com.rabbit.dao.ProjectPageDtoMapper;
import com.rabbit.dto.ProjectPageDto;
import com.rabbit.model.PageElement;
import com.rabbit.utils.UUIDUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.model.ProjectPage;
import com.rabbit.dao.ProjectPageMapper;
import com.rabbit.service.ProjectPageService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectPageServiceImpl implements ProjectPageService {

    @Resource
    private ProjectPageMapper projectPageMapper;
    @Resource
    private PageElementMapper pageElementMapper;
    @Autowired
    private ProjectPageDtoMapper projectPageDtoMapper;


    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        pageElementMapper.deleteByPageId(id);
        return projectPageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(ProjectPage record) {
        return projectPageMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(ProjectPage record) {
        return projectPageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ProjectPage record) {
        return projectPageMapper.updateByPrimaryKey(record);
    }

    @Override
    public ProjectPageDto selectDtoByPrimaryKey(Long id) {
        return projectPageDtoMapper.selectDtoByPrimaryKey(id);
    }

    @Override
    public List<ProjectPageDto> findDtoByProjectId(Long projectId) {
        return projectPageDtoMapper.findDtoByProjectId(projectId);
    }

    @Override
    public List<ProjectPageDto> findDtoByProjectIdAndPageName(Long projectId, String pageName) {
        return projectPageDtoMapper.findDtoByProjectIdAndPageName(projectId, pageName);
    }

    @Override
    public List<ProjectPageDto> findDtoByProjectIdAndPageNameAndIdNot(Long projectId, String pageName, Long notId) {
        return projectPageDtoMapper.findDtoByProjectIdAndPageNameAndIdNot(projectId, pageName, notId);
    }

    @Override
    public PageInfo<ProjectPageDto> findDtoByAllwithPage(int page, int pageSize, ProjectPage projectPage) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(projectPageDtoMapper.findDtoByAll(projectPage));
    }

    @Transactional
    @Override
    public void copyPageById(Long id) {
        ProjectPage projectPage = projectPageMapper.findById(id);
        if (projectPage == null) {
            throw new IllegalArgumentException("该页面元素已删除");
        }
        String newPageName = generateNewPageName(projectPage.getProjectId(), projectPage.getPageName());
        projectPage.setPageName(newPageName);
        projectPageMapper.insertSelective(projectPage);
        List<PageElement> pageElements = pageElementMapper.findByPageId(id);
        if (CollectionUtils.isNotEmpty(pageElements)) {
            for (PageElement pageElement : pageElements) {
                pageElement.setId(null);
                pageElement.setPageId(projectPage.getId());
            }
            pageElementMapper.insertList(pageElements);
        }
    }

    private String generateNewPageName(Long projectId, String pageName) {
        int i = 1;
        while (true) {
            pageName = pageName + "_temp";
            List<ProjectPage> byProjectIdAndPageName = projectPageMapper.findByProjectIdAndPageName(projectId, pageName);
            if (CollectionUtil.isEmpty(byProjectIdAndPageName)) {
                return pageName;
            }
            i++;
            if (i >= 10) {
                return pageName + UUIDUtil.getUUID();
            }
        }
    }
}


