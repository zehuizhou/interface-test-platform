package com.rabbit.dto;

import com.rabbit.model.SysDict;
import com.rabbit.model.SysDictValue;
import lombok.Data;

import java.util.List;

@Data
public class SysDictDto extends SysDict {
	private static final long serialVersionUID = 1L;
	private List<SysDictValue> sysDictValueList;
}
