package com.rabbit.dao;

import com.rabbit.model.PageElement;import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PageElementMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(PageElement record);

    PageElement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PageElement record);

    int updateByPrimaryKey(PageElement record);

    List<PageElement> findByAll(PageElement pageElement);

    List<PageElement> findByPageIdAndElementName(@Param("pageId") Long pageId, @Param("elementName") String elementName);

    List<PageElement> findByPageIdAndElementNameAndIdNot(@Param("pageId") Long pageId, @Param("elementName") String elementName, @Param("notId") Long notId);

    List<PageElement> findByPageId(@Param("pageId")Long pageId);

    int deleteByPageId(@Param("pageId")Long pageId);

    @Select("SELECT pe.* FROM t_project_page tp , t_page_element pe WHERE  tp.id = pe.page_id AND tp.project_id = #{projectId}")
    List<PageElement> findByProjectId(@Param("projectId") Long projectId);

    int insertList(@Param("list")List<PageElement> list);


}