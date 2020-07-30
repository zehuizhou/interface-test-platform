package com.rabbit.service;


import com.rabbit.model.SysUser;
import com.rabbit.dto.UserDto;

public interface UserService {

	SysUser saveUser(UserDto userDto);

	SysUser updateUser(UserDto userDto);

	SysUser getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

	void resetPassword(String username,String newPassword);

	int changeProjectId(Long id, Long projectId);

}
