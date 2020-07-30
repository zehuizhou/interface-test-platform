package com.rabbit.dto;

import com.rabbit.model.Project;
import com.rabbit.model.Role;
import com.rabbit.model.SysUser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class UserDto extends SysUser {

	private static final long serialVersionUID = -184009306207076712L;

	private List<Long> roleIds;
	private List<Long> projectIds;
	private List<Role> roles;
	private List<Project> projects;
}
