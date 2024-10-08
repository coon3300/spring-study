package com.yedam.app.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.yedam.app.emp.service.EmpVO;

import lombok.extern.slf4j.Slf4j;

@Aspect // AOP의 설정
@Component
@Slf4j // lombok
public class CommonAspect {

	
	// 포인트컷 : 조인포인트(@Service의 메소드들)중에서
	//			Advice(횡단관심, 부가기능)이 적용될 메소드 조건
//    @Pointcut("within(com.yedam.app.emp.service.impl.*)") // 검색 조건
//	public void empPointCut() {}
    @Pointcut("within(com.yedam.app.**.service.impl.*)") // 검색 조건
    public void servicePointCut() {}
      
    // Weaving : 포인트컷 + 동작시점 + Advice
    //@Before("empPointCut()")    
    public void beforeAdvice(JoinPoint joinPoint) {
    	/*
    	 * 
    	 */
    	// Advice를 구현
        log.debug("AOP 적용");
        String signStr = joinPoint.getSignature().toString();
        Object[] args = joinPoint.getArgs();
        log.error("######### 실행 : " + signStr);
        for(Object arg : args) {
        	log.error("매개변수 : ",arg);
        	if(arg instanceof Integer) {
        		System.err.println((Integer)arg);
        		
        	}else if(arg instanceof EmpVO) {
        		System.err.println((EmpVO)arg);
        	}
        }
    }
    
    @Around("servicePointCut()")
    public Object executeTime(ProceedingJoinPoint joinPoint) throws Throwable {
    	/*
    	 * 
    	String signStr = joinPoint.getSignature().toString();
    	System.out.println("=== 시작 : " + signStr);
    	
    	
    	System.out.println("=== 핵심 기능 전 실행 : " + System.currentTimeMillis());
    	
    	try {
    		// 비지니스 메소드를 실행
    		Object obj = joinPoint.proceed();
    		return obj;
    	}finally {
    		// 공통 기능
    		System.out.println("=== 핵심 기능 후 실행 : " + System.currentTimeMillis());
    		System.out.println("=== 끝 : " + signStr);
    	}
    	 */
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        
        log.info("Starting {} in {}", methodName, className);
        
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        
        log.info("Finished {} in {} ({}ms)", methodName, className, elapsedTime);
        
        return result;
    }
}