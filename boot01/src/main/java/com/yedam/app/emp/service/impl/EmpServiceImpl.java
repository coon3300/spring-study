package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Timed("my.emp")
@Slf4j
@Service // AOP 적용가능한 Bean
public class EmpServiceImpl implements EmpService {
	//private MeterRegistry registry;
	private EmpMapper empMapper;
	
	@Autowired // 생성자 1개면 자동 autowired됨.
	EmpServiceImpl(EmpMapper empMapper, MeterRegistry registry){
//	EmpServiceImpl(EmpMapper empMapper, MeterRegistry registry){
		this.empMapper = empMapper;
		//this.registry = registry;
	}
	
	@Override
	public List<EmpVO> empList() {
		/*
		 * 
		Timer timer = Timer.builder("my.list")
				.tag("class", this.getClass().getName())
				.tag("method", "empList")
				.description("list")
				.register(registry);
		
		timer.record(() -> {
			log.info("empList");
			sleep(500);
		});
		 */
		
		return empMapper.selectEmpAllList();
	}
	private static void sleep(int i) {
		
		try {
			Thread.sleep(i + new Random().nextInt(500));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int empInsert(EmpVO empVO) {
		int result = empMapper.insertEmpInfo(empVO);
		
		return result == 1 ? empVO.getEmployeeId() : -1;
	}

	@Override
	public Map<String, Object> empUpdate(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", empVO);
		/**
		 * AJAX
		{
			"result" : true,
			"target" : {
							employeeId : 100,
							lastName : "King",
							...
						}
		}
		 */
		
		return map;
	}

	@Override
	public Map<String, Object> empDelete(int empId) {
		Map<String, Object> map = new HashMap<>();
		// 삭제가 안될 경우 : {}
		// 삭제가 될 경우 : { "employeeId" : 104}
		int result = empMapper.deleteEmpInfo(empId);
		
		if(result == 1) {
			map.put("employeeId", empId); 
		}
		
		return map;
	}

}
