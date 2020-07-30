package com.rabbit.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.model.ProjectPage;
import com.rabbit.model.po.GlobalVar;
import com.rabbit.utils.UUIDUtil;
import com.rabbit.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.rabbit.dao.PageElementMapper;
import com.rabbit.model.PageElement;
import com.rabbit.service.PageElementService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PageElementServiceImpl implements PageElementService {

    @Resource
    private PageElementMapper pageElementMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return pageElementMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(PageElement record) {
        record.setUpdateBy(UserUtil.getLoginUser().getUsername());
        record.setCreateBy(UserUtil.getLoginUser().getUsername());
        return pageElementMapper.insertSelective(record);
    }

    @Override
    public PageElement selectByPrimaryKey(Long id) {
        return pageElementMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PageElement record) {
        return pageElementMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PageElement record) {
        record.setUpdateBy(UserUtil.getLoginUser().getUsername());
        return pageElementMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<PageElement> findByAllwithPage(int page, int pageSize, PageElement pageElement) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(pageElementMapper.findByAll(pageElement));
    }

    @Override
    public List<PageElement> findByPageIdAndElementName(Long pageId, String elementName) {
        return pageElementMapper.findByPageIdAndElementName(pageId, elementName);
    }

    @Override
    public List<PageElement> findByPageIdAndElementNameAndIdNot(Long pageId, String elementName, Long notId) {
        return pageElementMapper.findByPageIdAndElementNameAndIdNot(pageId, elementName, notId);
    }

    @Override
    public List<PageElement> findByProjectId(Long projectId) {
        return pageElementMapper.findByProjectId(projectId);
    }

    @Override
    public void copyElemenById(Long id) {
        PageElement pageElement = pageElementMapper.selectByPrimaryKey(id);
        if (pageElement == null) {
            throw new IllegalArgumentException("该页面元素已删除");
        }
        String newElementName = generateNewElementName(pageElement.getPageId(), pageElement.getElementName());
        pageElement.setElementName(newElementName);
        pageElementMapper.insertSelective(pageElement);
    }

    @Override
    @Transactional
    public void batchSaveElements(List<PageElement> pageElements) {
        if (CollectionUtils.isNotEmpty(pageElements)) {
            int i = 1;
            for (PageElement pageElement : pageElements) {
                if (StringUtils.isEmpty(pageElement.getElementName())
                        || StringUtils.isEmpty(pageElement.getByType())
                        || StringUtils.isEmpty(pageElement.getByValue())) {
                    throw new IllegalArgumentException("第[" + i + "]行有必填项为空");
                }
                i++;
            }
            for (PageElement pageElement : pageElements) {
                if (pageElement.getId() == null) {
                    pageElementMapper.insertSelective(pageElement);
                } else {
                    pageElementMapper.updateByPrimaryKey(pageElement);
                }
            }
        }
    }

    private String generateNewElementName(Long pageId, String elementName) {
        int i = 1;
        while (true) {
            elementName = elementName + "_temp";
            List<PageElement> byPageIdAndElementName = pageElementMapper.findByPageIdAndElementName(pageId, elementName);
            if (CollectionUtil.isEmpty(byPageIdAndElementName)) {
                return elementName;
            }
            i++;
            if (i >= 10) {
                return elementName + UUIDUtil.getUUID();
            }
        }
    }
}




