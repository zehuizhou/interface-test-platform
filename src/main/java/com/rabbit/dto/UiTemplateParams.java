package com.rabbit.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UiTemplateParams  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long planLogId;
	private String createTime;
	private String webip;
	private String webport;
	private String contextpath;
	private String jobname;
	private int businesstime = 0;
	private int businesscount = 0;
	private int casecount = 0;
	private int casesuc = 0;
	private int casefail = 0;
	private int caseskip = 0;
}
