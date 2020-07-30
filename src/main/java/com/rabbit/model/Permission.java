package com.rabbit.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
public class Permission extends BaseEntity<Long> {

	private static final long serialVersionUID = 6180869216498363919L;

	private Long parentId;
	private String name;
	private String icon;
	private String path;
	private String redirect;
	private String activeMenu;
	private Integer type;
	private String permission;
	private Integer sort;
	private String component;
	private List<Permission> children;
}
