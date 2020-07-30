package com.rabbit.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.dao.RoleDao;
import com.rabbit.dao.UserDao;
import com.rabbit.dto.UserDto;
import com.rabbit.model.ErrorInfo;
import com.rabbit.model.ResponseInfo;
import com.rabbit.model.SysUser;
import com.rabbit.service.ProjectService;
import com.rabbit.service.SysLogService;
import com.rabbit.service.UserService;
import com.rabbit.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户相关接口
 */

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SysLogService sysLogService;

    @PostMapping
//    @PreAuthorize("hasAuthority('sys:user:add')")
    public ResponseInfo saveUser(@RequestBody UserDto userDto) {
        Long projectId = userDto.getProjectId();
        if (projectId == null || projectId == -1) {
            return new ResponseInfo(false, new ErrorInfo(123, "请选择默认项目"));
        }
        SysUser u = userService.getUser(userDto.getUsername());
        if (u != null) {
            throw new IllegalArgumentException(userDto.getUsername() + "已存在");
        }
        userService.saveUser(userDto);
        return new ResponseInfo(true, "保存用户成功");
    }

    @PutMapping
//    @PreAuthorize("hasAuthority('sys:user:add')")
    public ResponseInfo updateUser(@RequestBody UserDto userDto) {
        Long projectId = userDto.getProjectId();
        if (projectId == null || projectId == -1) {
            return new ResponseInfo(false, new ErrorInfo(123, "请选择默认项目"));
        }
        return new ResponseInfo(true, userService.updateUser(userDto));
    }

    @PutMapping(params = "headImgUrl")
    public void updateHeadImgUrl(String headImgUrl) {
        SysUser user = UserUtil.getLoginUser();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setHeadImgUrl(headImgUrl);

        userService.updateUser(userDto);
        log.debug("{}修改了头像", user.getUsername());
    }

    @PutMapping("/changePassword")
    @PreAuthorize("hasAuthority('sys:user:changePassword')")
    public ResponseInfo changePassword(@RequestParam(value = "username") String username, @RequestParam(value = "oldPassword") String oldPassword, @RequestParam(value = "newPassword") String newPassword) {
        userService.changePassword(username, oldPassword, newPassword);
        return new ResponseInfo(true, "修改密码成功");
    }

    @PutMapping("/changeProjectId")
    public ResponseInfo changeProjectId(@RequestParam(value = "projectId") Long projectId) {
        userService.changeProjectId(UserUtil.getLoginUser().getId(), projectId);
        return new ResponseInfo(true, "修用户默认项目成功");
    }

    @PutMapping("/resetPassword")
//    @PreAuthorize("hasAuthority('sys:user:resetPassword')")
    public ResponseInfo resetPassword(@RequestParam(value = "username") String username, @RequestParam(value = "newPassword") String newPassword) {
        String loginUser = UserUtil.getLoginUser().getUsername();
        log.info(username + "=========" + loginUser);
        if (username.equals("admin") & !username.equals(loginUser)) {
            return new ResponseInfo(false, new ErrorInfo(95, "admin账号必须admin用户进行重置"));
        }
        userService.resetPassword(username, newPassword);
        sysLogService.save(UserUtil.getLoginUser().getId(), "重置密码", true, "");
        return new ResponseInfo(true, "重置密码成功");
    }

    @GetMapping
//    @PreAuthorize("hasAuthority('sys:user:query')")
    public ResponseInfo listUsers(
            @RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchUser") String serchUser) {
//        Page<UserDto> page = PageHelper.startPage(pageNum, pageSize);
        JSONObject jsonObject = JSONObject.parseObject(serchUser);
        UserDto userDto = JSONObject.toJavaObject(jsonObject, UserDto.class);
        PageHelper.startPage(pageNum, pageSize);
        List<UserDto> list = userDao.listByPage(userDto);
        for (UserDto userDto1 : list) {
            userDto1.setRoles(roleDao.listByUserId(userDto1.getId()));
            userDto1.setRoleIds(roleDao.listIdByUserId(userDto1.getId()));
            userDto1.setProjects(projectService.listByUserId(userDto1.getId()));
        }
        PageInfo page = new PageInfo(list);
//        System.out.println(page);
        return new ResponseInfo(true, page);
    }

    @GetMapping("/current")
    public SysUser currentUser() {
        return UserUtil.getLoginUser();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public SysUser user(@PathVariable Long id) {
        return userDao.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseInfo deleteUser(@PathVariable Long id) {
        userDao.deleteUserById(id);
        userDao.deleteUserRole(id);
        return new ResponseInfo(true, "删除用户成功");
    }


}
