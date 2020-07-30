package com.rabbit.dao;

import com.rabbit.dto.UserDto;
import com.rabbit.model.Project;
import com.rabbit.model.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_user(project_id,username, password, nickname, headImgUrl, phone, telephone, email, birthday, sex, status, createTime, updateTime) values(#{projectId}, #{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, now(), now())")
    int save(SysUser user);

    @Select("select * from sys_user t where t.id = #{id}")
    SysUser getById(Long id);

    @Select("select * from sys_user t where t.username = #{username}")
    SysUser getUser(String username);

    @Update("update sys_user t set t.project_id = #{projectId} where t.id = #{id}")
    int changeProjectId(@Param("id") Long id, @Param("projectId") Long projectId);

    @Update("update sys_user t set t.password = #{password} where t.id = #{id}")
    int changePassword(@Param("id") Long id, @Param("password") String password);

    List<UserDto> listByPage(
            @Param("params") UserDto userDto);

    @Delete("delete from sys_role_user where userId = #{userId}")
    int deleteUserRole(Long userId);

    @Delete("delete from sys_user_project where userId = #{userId}")
    int deleteUserProject(Long userId);

    @Delete("delete from sys_user where id = #{userId}")
    int deleteUserById(Long userId);

    int saveUserRoles(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

    int saveUserProjects(@Param("userId") Long userId, @Param("projectIds")  List<Long> projectIds);

    int update(SysUser user);
}
