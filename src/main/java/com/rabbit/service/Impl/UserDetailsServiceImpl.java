package com.rabbit.service.Impl;

import com.rabbit.dao.RoleDao;
import com.rabbit.dto.LoginUser;
import com.rabbit.model.Permission;
import com.rabbit.model.Project;
import com.rabbit.model.Role;
import com.rabbit.model.SysUser;
import com.rabbit.model.SysUser.Status;
import com.rabbit.service.PermissionService;
import com.rabbit.service.ProjectService;
import com.rabbit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * spring security登陆处理<br>
 */
@Service
@Slf4j
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ProjectService projectService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.getUser(username);
        if (sysUser == null) {
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        } else if (sysUser.getStatus() == Status.LOCKED) {
            throw new LockedException("用户被锁定,请联系管理员");
        } else if (sysUser.getStatus() == Status.DISABLED) {
            throw new DisabledException("用户已作废");
        }

        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(sysUser, loginUser);
        List<Permission> permissions;
        List<Project> projects;

        List<Role> list = roleDao.listByUserId(sysUser.getId());
        List<Role> admin = list.stream().filter(role -> role.getName().equals("admin")).collect(Collectors.toList());
        if (admin.size() > 0) {
            log.debug("如果有admin角色，就有所有权限");
            permissions = permissionService.getPermissionAll();
            projects = projectService.findAll();
        } else {
            permissions = permissionService.getPermissionByUserId(sysUser.getId());
            projects = projectService.selectAllByUserId(sysUser.getId());
        }
        loginUser.setPermissions(permissions);
        loginUser.setProjects(projects);
        return loginUser;
    }

}
