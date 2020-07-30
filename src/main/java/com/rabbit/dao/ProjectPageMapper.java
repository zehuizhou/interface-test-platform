package com.rabbit.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.rabbit.model.ProjectPage;

public interface ProjectPageMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(ProjectPage record);

    int updateByPrimaryKeySelective(ProjectPage record);

    int updateByPrimaryKey(ProjectPage record);

    ProjectPage findById(@Param("id")Long id);

    List<ProjectPage> findByProjectIdAndPageName(@Param("projectId")Long projectId,@Param("pageName")String pageName);

    List<ProjectPage> findByProjectIdAndPageNameAndIdNot(@Param("projectId")Long projectId,@Param("pageName")String pageName,@Param("notId")Long notId);


}