package com.rabbit.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.dao.RoleDao;
import com.rabbit.dto.RoleDto;
import com.rabbit.dto.UserDto;
import com.rabbit.model.ResponseInfo;
import com.rabbit.model.Role;
import com.rabbit.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 角色相关接口
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleDao roleDao;

    @PostMapping
//    @PreAuthorize("hasAuthority('sys:role:add')")
    public ResponseInfo saveRole(@RequestBody RoleDto roleDto) {
        roleService.saveRole(roleDto);
        return new ResponseInfo(true, "保存用户成功");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:role:query')")
    public Role get(@PathVariable Long id) {
        return roleDao.getById(id);
    }


    //	查询所有用户
    @GetMapping("/allByPage")
//	@PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
    public ResponseInfo rolesByPage(@RequestParam(value = "pageNum") int pageNum, @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "serchRole") String serchRole) {
        JSONObject jsonObject = JSONObject.parseObject(serchRole);
        RoleDto roleDto = JSONObject.toJavaObject(jsonObject, RoleDto.class);
        PageHelper.startPage(pageNum, pageSize);
        List<RoleDto> list = roleDao.listByPage(roleDto);
        PageInfo page = new PageInfo(list);
        return new ResponseInfo(true, page);
    }

    //	查询所有用户
    @GetMapping("/all")
//	@PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
    public ResponseInfo roles() {
        return new ResponseInfo(true, roleDao.listAll());
    }

    @GetMapping(params = "userId")
    @PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
    public List<Role> roles(Long userId) {
        return roleDao.listByUserId(userId);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('sys:role:del')")
    public ResponseInfo delete(@PathVariable Long id) {
        roleService.deleteRole(id);
        return new ResponseInfo(true, "删除角色成功");
    }
}
