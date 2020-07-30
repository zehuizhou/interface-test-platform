package com.rabbit.service.Impl;


import com.rabbit.dao.UserDao;
import com.rabbit.dto.LoginUser;
import com.rabbit.model.Project;
import com.rabbit.model.SysUser;
import com.rabbit.dto.UserDto;
import com.rabbit.model.SysUser.Status;
import com.rabbit.service.TokenService;
import com.rabbit.service.UserService;
import com.rabbit.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    @Override
    @Transactional
    public SysUser saveUser(UserDto userDto) {
        SysUser user = userDto;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getStatus() == null) {
            user.setStatus(Status.VALID);
        }
        userDao.save(user);
        saveUserRoles(user.getId(), userDto.getRoleIds());
        saveUserProjects(user.getId(), userDto.getProjectIds());

        log.debug("新增用户{}", user.getUsername());
        return user;
    }

    private void saveUserRoles(Long userId, List<Long> roleIds) {
        if (roleIds != null) {
            userDao.deleteUserRole(userId);
            if (!CollectionUtils.isEmpty(roleIds)) {
                userDao.saveUserRoles(userId, roleIds);
            }
        }
    }

    @Override
    public SysUser getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        SysUser u = userDao.getUser(username);
        if (u == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        if (!passwordEncoder.matches(oldPassword, u.getPassword())) {
            throw new IllegalArgumentException("旧密码错误");
        }

        userDao.changePassword(u.getId(), passwordEncoder.encode(newPassword));

        log.debug("修改{}的密码", username);
    }

    @Override
    public int changeProjectId(Long id, Long projectId) {
        LoginUser loginUser = UserUtil.getLoginUser();
        loginUser.setProjectId(projectId);
        tokenService.refresh(loginUser);
        return userDao.changeProjectId(id, projectId);
    }

    @Override
    public void resetPassword(String username, String newPassword) {
        SysUser u = userDao.getUser(username);
        if (u == null) {
            throw new IllegalArgumentException("用户不存在");
        }

        userDao.changePassword(u.getId(), passwordEncoder.encode(newPassword));

        log.debug("修改{}的密码", username);
    }

    @Override
    @Transactional
    public SysUser updateUser(UserDto userDto) {
        userDao.update(userDto);
        saveUserRoles(userDto.getId(), userDto.getRoleIds());
        saveUserProjects(userDto.getId(), userDto.getProjectIds());

        return userDto;
    }

    private void saveUserProjects(Long userId, List<Long> projectIds) {
        if (projectIds != null) {
            userDao.deleteUserProject(userId);
            if (!CollectionUtils.isEmpty(projectIds)) {
                userDao.saveUserProjects(userId, projectIds);
            }
        }
    }

}
