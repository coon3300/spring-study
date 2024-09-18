package com.yedam.app.dept.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class DeptServiceImpl implements DeptService{
	private final MeterRegistry registry;
	private DeptMapper deptMapper;
	
	@Autowired
	public DeptServiceImpl(DeptMapper deptMapper, MeterRegistry registry) {
		this.deptMapper = deptMapper;
		this.registry = registry;
	}
	
	@Counted("my.list")
	@Override
	public List<DeptVO> deptList() {
		return deptMapper.selectDeptAll();
	}

	@Override
	public DeptVO deptInfo(DeptVO deptVO) {
		return deptMapper.selectDeptInfo(deptVO);
	}

	@Override
	public int deptInsert(DeptVO deptVO) {
		int result = deptMapper.insertDeptInfo(deptVO);
		
		
		Counter.builder("my.dept")
			.tag("class", this.getClass().getName())
			.tag("method", "deptInsert")
			.description("dept")
			.register(registry).increment();
		
		
		return result == 1 ? deptVO.getDepartmentId() : -1;
	}

	@Override
	public Map<String, Object> deptUpdate(DeptVO deptVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = deptMapper.updateDeptInfo(deptVO.getDepartmentId(), deptVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", deptVO);
		
		return map;
	}

	@Override
	public Map<String, Object> deptDelete(int deptId) {
		Map<String, Object> map = new HashMap<>();
		
		int result = deptMapper.deleteDeptInfo(deptId);
		
		Counter.builder("my.dept")
		.tag("class", this.getClass().getName())
		.tag("method", "deptDelete")
		.description("dept")
		.register(registry).increment();		
		
		if(result == 1) {
			map.put("deparmentId", deptId);
		}
		
		
		return map;
	}

}
