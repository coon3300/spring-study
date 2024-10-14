package com.sjc.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter
public class LoginUserVO implements UserDetails{
	private UserVO userVO;
	
	public LoginUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority(userVO.getRoleName()));
		auths.add(new SimpleGrantedAuthority(userVO.getUserName()));
		auths.add(new SimpleGrantedAuthority(userVO.getDeptCode()));
		auths.add(new SimpleGrantedAuthority(userVO.getDeptName()));
		return auths;
	}

	@Override
	public String getPassword() {
		return userVO.getPassword();
	}

	@Override
	public String getUsername() {
		return userVO.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() { // 계정 만료 여부
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 계정 잠금 여부 
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 계정 패스워드 만료 여부
		return true;
	}

	@Override
	public boolean isEnabled() { // 계정 사용 여부
		return true;
	}

}
