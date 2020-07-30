package com.rabbit.dto;

import com.rabbit.model.Permission;
import com.rabbit.model.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class RoleDto extends Role {
	private static final long serialVersionUID = 4218495592167610193L;
	private List<Long> permissionIds;
	private List<Permission> permissions;
}
