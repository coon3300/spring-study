package com.yedam.app.spring.xml;

public class Restaurant {
	private Chef chef;
	
	// 생성자 인젝션
	public Restaurant(Chef chef) {
		this.chef = chef;
		System.out.println("생성자 인젝션");
	}
	
	// 세터 인젝션 : 기본 생성자 필요.
	Restaurant() {
		System.out.println("기본 생성자");
		
	}
	
	public void setChef(Chef chef) {
		this.chef = chef;
		System.out.println("세터 인젝션");
	}
	
	public void run() {
		chef.cooking();
	}
}
