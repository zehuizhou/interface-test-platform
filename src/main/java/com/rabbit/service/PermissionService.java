package com.rabbit.service;


import com.rabbit.model.Permission;

import java.util.List;

public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);

	List<Permission>  getPermissionAll();
	List<Permission>  getPermissionByUserId(Long userId);
}
