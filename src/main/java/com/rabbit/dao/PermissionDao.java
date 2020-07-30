package com.rabbit.dao;

import com.rabbit.model.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

@Mapper
public interface PermissionDao {

    @Select("select * from sys_permission t order by t.sort")
    List<Permission> listAll();

    @Select("select * from sys_permission t where t.type in( 1 ,4) order by t.sort")
    List<Permission> listAllMenu();

    @Select("select distinct p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId inner join sys_role_user ru on ru.roleId = rp.roleId where ru.userId = #{userId} order by p.sort")
    List<Permission> listByUserId(Long userId);

    @Select("select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId where rp.roleId = #{roleId} order by p.sort")
    List<Permission> listByRoleId(Long roleId);

    @Select("select p.id from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId where NOT EXISTS (SELECT 1 FROM sys_permission p1 WHERE p.id = p1.parentId ) and  rp.roleId = #{roleId} order by p.sort")
    List<Long> childListIDByRoleId(Long roleId);

    @Select("select * from sys_permission t where t.id = #{id}")
    Permission getById(Long id);

    @Insert("INSERT INTO sys_permission(parentId, NAME, icon, path, TYPE, permission, sort ,component,redirect,active_menu) VALUES(#{parentId}, #{name}, #{icon}, #{path}, #{type}, #{permission}, #{sort} ,#{component},#{redirect},#{activeMenu})")
    int save(Permission permission);

    @Update("UPDATE sys_permission t SET parentId = #{parentId}, name = #{name}, icon = #{icon}, path = #{path},redirect = #{redirect}, active_menu = #{activeMenu},type = #{type}, permission = #{permission}, sort = #{sort}, component = #{component} where t.id = #{id}")
    int update(Permission permission);

    @Delete("delete from sys_permission where id = #{id}")
    int delete(Long id);

    @Delete("delete from sys_permission where parentId = #{id}")
    int deleteByParentId(Long id);

    @Delete("delete from sys_role_permission where permissionId = #{permissionId}")
    int deleteRolePermission(Long permissionId);
}
