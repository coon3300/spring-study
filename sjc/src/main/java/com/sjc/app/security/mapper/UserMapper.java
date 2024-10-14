package com.sjc.app.security.mapper;

import com.sjc.app.security.service.UserVO;

public interface UserMapper {
	public UserVO getUserInfo(String username);
}
