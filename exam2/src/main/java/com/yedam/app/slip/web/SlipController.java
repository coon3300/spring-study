package com.yedam.app.slip.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.slip.service.Slip;
import com.yedam.app.slip.service.SlipService;

/**
 * Handles requests for the application home page.
 */
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST})
@Controller
public class SlipController {
	
	@Autowired
	SlipService slipService;
	
	private static final Logger logger = LoggerFactory.getLogger(SlipController.class);
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/**
	 * 급여 지급 등록 
	 * @param list
	 * @return 
	 */
	@PostMapping("api/insertslip")
	@ResponseBody
	public Map<String, Object> insertslip(@RequestBody List<Slip> list) {
		return slipService.insertSlip(list);
	}
	
	
	@PostMapping("api/insertslip2")
	@ResponseBody
	public Map<String, Object> insertslip2(@RequestBody List<Slip> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> errEmps =  slipService.insertSlip2(list);
		map.put("result", "success");
		map.put("total", list.size());
		map.put("empList", errEmps);
		return map;
	}
}
