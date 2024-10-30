package com.yedam.app.slip.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.slip.mapper.SlipMapper;
import com.yedam.app.slip.service.Slip;
import com.yedam.app.slip.service.SlipService;
@Service
public class SlipServiceImpl implements SlipService{

	@Autowired	SlipMapper dao;
	
	@Override
	public Map<String, Object> insertSlip(List<Slip> slip) {
		int cnt = 0;
		boolean isSuccess = false;
		List<String> list = new ArrayList<String>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		//테이블 입력
		for(Slip data : slip) {
			cnt++;
			if(data.getSlipAmount() <= 20000) {
				dao.insertSlip(data);				
			} else {
				list.add(data.getCustomer().split("_")[0]);
				//list.add(data.getCustomer().substring(0, 3));				
			}
		}
		
		isSuccess = true;
		
		map.put("result", isSuccess);
		map.put("total", cnt);
		map.put("empList", list);
		
		
		return map;  // 처리 건수 리턴;
	}

	public List<String> insertSlip2(List<Slip> slip) {

		List<String> list = new ArrayList<String>();
		
		//테이블 입력
		for(Slip data : slip) {
			if(data.getSlipAmount() <= 20000) {
				dao.insertSlip(data);				
			} else {
				list.add(data.getCustomer().split("_")[0]);
				//list.add(data.getCustomer().substring(0, 3));				
			}
		}
		
		return list;  // 처리 건수 리턴;
	}
}
