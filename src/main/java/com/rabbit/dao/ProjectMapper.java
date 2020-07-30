package com.rabbit.dao;

import com.rabbit.model.Project;import org.apache.ibatis.annotations.Delete;import org.apache.ibatis.annotations.Param;import org.apache.ibatis.annotations.Select;import java.util.List;

public interface ProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<Project> findAll();

    List<Project> findByAll(Project project);

    List<Project> findByProjectName(@Param("projectName") String projectName);

    List<Project> findByProjectNameAndIdNot(@Param("projectName") String projectName, @Param("notId") Long notId);

    @Select("SELECT * FROM sys_project sp ,sys_user_project up WHERE sp.id = up.projectId AND up.userId = #{userId}")
    List<Project> selectAllByUserId(Long userId);

    @Delete("delete from sys_user_project where projectId = #{projectId}")
    int deleteUserProjectByProjectId(Long projectId);

    @Select("SELECT sp.* FROM sys_user_project up ,sys_project sp WHERE up.projectId = sp.id AND up.userId = #{userId}")
    List<Project> listByUserId(Long userId);
}