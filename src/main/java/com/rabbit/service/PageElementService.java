package com.rabbit.service;

import com.github.pagehelper.PageInfo;
import com.rabbit.model.PageElement;

import java.util.List;

public interface PageElementService {


    int deleteByPrimaryKey(Long id);

    int insertSelective(PageElement record);

    PageElement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PageElement record);

    int updateByPrimaryKey(PageElement record);

    PageInfo<PageElement> findByAllwithPage(int page, int pageSize, PageElement pageElement);

    List<PageElement> findByPageIdAndElementName(Long pageId, String elementName);

    List<PageElement> findByPageIdAndElementNameAndIdNot(Long pageId, String elementName, Long notId);

    List<PageElement> findByProjectId(Long projectId);

    void copyElemenById(Long id);

    void batchSaveElements(List<PageElement> pageElements);
}




