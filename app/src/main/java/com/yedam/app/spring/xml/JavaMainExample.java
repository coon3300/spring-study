package com.yedam.app.spring.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class JavaMainExample {

	public static void main(String[] args) {
		Chef cf = new Chef();
		
		Restaurant res = new Restaurant(cf); 
				
		res.run();
	}

}
