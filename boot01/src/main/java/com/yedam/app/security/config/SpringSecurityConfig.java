package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {
	@Bean // 비밀번호 암호화
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); //BCrypt 단방향 해시 알고리즘
	}
/*
 * 	
	@Bean // 메모리상 인증정보 등록 => 테스트 전용
	InMemoryUserDetailsManager inMemoryUserDetailsService() {
		UserDetails user = 	User.builder()
								.username("user1")
								.password(passwordEncoder().encode("1234"))
								.roles("USER") // ROLE_USER : ROLE_ 자동 추가
//								.authorities("ROLE_USER")
								.build();
		
		UserDetails admin = 	User.builder()
									.username("admin1")
									.password(passwordEncoder().encode("1234"))
//									.roles("ADMIN") // ROLE_USER : ROLE_ 자동 추가
									.authorities("ROLE_ADMIN", "ROLE_USER")
									.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
 */
	
	// 인증 및 인가
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 적용될 Security 설정
		// => URI에 적용될 권한
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/", "all").permitAll()
//				.requestMatchers("/user/**").hasRole("USER")
				.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
	            // Spring Boot Admin과 Actuator 엔드포인트 허용
	            .requestMatchers("/actuator/**", "/instances/**", "/assets/**", "/login").permitAll()
				.anyRequest().authenticated() // 위 경로 제외 인증되면 접속 허가. 권한 필요 없음.
		)
		.formLogin(formlogin -> formlogin
				.defaultSuccessUrl("/all"))
		.logout(logout -> logout
				.logoutSuccessUrl("/all")
				.invalidateHttpSession(true)); // 로그아웃 시 세션 삭제
		
		http.csrf(csrf -> csrf.disable()); // 개발용.th에서도 삭제
				
		return http.build();
	}
}
