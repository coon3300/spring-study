package com.yedam.app.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainExample {

	public static void main(String[] args) {
		//ApplicationContext ctx = new GenericXmlApplicationContext("file:src/main/resources/applicationContext.xml"); // 컨테이너 생성.
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml"); // 컨테이너 생성.
		
		TV tv = (TV)ctx.getBean("tv");
		tv.turnOn();
		
		TV subTv = (TV)ctx.getBean(TV.class);
		subTv.turnOn();
		
		if(tv == subTv) { // 싱글톤.
			System.out.println("같은 bean입니다.");
		}else {
			System.out.println("다른 bean입니다.");
			
		}
	}

}
