package com.rabbit.controller;

import com.rabbit.dao.PermissionDao;
import com.rabbit.dto.LoginUser;
import com.rabbit.model.Permission;
import com.rabbit.model.ResponseInfo;
import com.rabbit.service.PermissionService;
import com.rabbit.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限相关接口
 */
@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/current")
    public ResponseInfo permissionsCurrent() {
        LoginUser loginUser = UserUtil.getLoginUser();
        return new ResponseInfo(true, loginUser);
    }

    @GetMapping
//	@PreAuthorize("hasAuthority('sys:menu:query')")
    public ResponseInfo permissionsList() {
        List<Permission> permissionAll = permissionService.getPermissionAll();
        return new ResponseInfo(true, permissionAll);
    }

    @GetMapping("/allMenu")
//	@PreAuthorize("hasAuthority('sys:menu:query')")
    public ResponseInfo permissionsAll() {
        List<Permission> permissionsAll = permissionDao.listAllMenu();
        return new ResponseInfo(true, permissionsAll);
    }


    @GetMapping(params = "roleId")
    @PreAuthorize("hasAnyAuthority('sys:menu:query','sys:role:query')")
    public List<Permission> listByRoleId(Long roleId) {
        return permissionDao.listByRoleId(roleId);
    }

    @GetMapping("/childListID")
//	@PreAuthorize("hasAnyAuthority('sys:menu:query','sys:role:query')")
    public ResponseInfo listIDByRoleId(@RequestParam(value = "roleId") Long roleId) {
        return new ResponseInfo(true, permissionDao.childListIDByRoleId(roleId));
    }

    @PostMapping
//	@PreAuthorize("hasAuthority('sys:menu:add')")
    public ResponseInfo save(@RequestBody Permission permission) {
        permissionDao.save(permission);
        return new ResponseInfo(true, "保存资源成功");
    }

    @GetMapping("/{id}")
//	@PreAuthorize("hasAuthority('sys:menu:query')")
    public ResponseInfo get(@PathVariable Long id) {
        Permission permission = permissionDao.getById(id);
        return new ResponseInfo(true, permission);
    }

    @PutMapping
//	@PreAuthorize("hasAuthority('sys:menu:add')")
    public ResponseInfo update(@RequestBody Permission permission) {
        permissionService.update(permission);
        return new ResponseInfo(true, "编辑资源成功");
    }

    /**
     * 校验权限
     *
     * @return
     */
    @GetMapping("/owns")
    public Set<String> ownsPermission() {
        List<Permission> permissions = UserUtil.getLoginUser().getPermissions();
        if (CollectionUtils.isEmpty(permissions)) {
            return Collections.emptySet();
        }
        return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
                .map(Permission::getPermission).collect(Collectors.toSet());
    }

    @DeleteMapping("/{id}")
//	@PreAuthorize("hasAuthority('sys:menu:del')")
    public ResponseInfo delete(@PathVariable Long id) {
        permissionService.delete(id);
        return new ResponseInfo(true, "删除资源成功");
    }
}
