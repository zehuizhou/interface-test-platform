package com.rabbit.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rabbit.model.Permission;
import com.rabbit.model.Project;
import com.rabbit.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class LoginUser extends SysUser implements UserDetails {

    private static final long serialVersionUID = -1379274258881257107L;

    private List<Permission> permissions;

    private List<Project> projects;

    private String token;
    /**
     * 登陆时间戳（毫秒）
     */
    private Long loginTime;
    /**
     * 过期时间戳
     */
    private Long expireTime;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<SimpleGrantedAuthority> collect = permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
//                .map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        setAuth(permissions, authorities);
        log.info("获取指令权限=====" + this.getId() + authorities);
        return authorities;
    }

    private void setAuth(List<Permission> p, Collection<SimpleGrantedAuthority> a) {
        List<Permission> list = p.parallelStream().filter(b -> !StringUtils.isEmpty(b.getPermission())).collect(Collectors.toList());
        list.parallelStream().forEach(c -> {
                    a.add(new SimpleGrantedAuthority(c.getPermission()));
                    List<Permission> child = c.getChildren();
                    if (!CollectionUtils.isEmpty(child))
                        setAuth(child, a);
                }
        );
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        // do nothing
    }

    // 账户是否未过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return getStatus() != Status.LOCKED;
    }

    // 密码是否未过期
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否激活
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

}
