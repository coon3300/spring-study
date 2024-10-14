package com.sjc.app.security.service;

import lombok.Data;

@Data
public class UserVO {
	private String loginId;
	private String password;
	private String roleName;
	private String userName;
	private String deptCode;
	private String deptName;
}
