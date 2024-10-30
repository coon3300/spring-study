package com.yedam.app.slip.service;

import java.util.List;
import java.util.Map;

public interface SlipService {
	public Map<String, Object> insertSlip(List<Slip> slip) ;
	public List<String> insertSlip2(List<Slip> slip) ;
}
