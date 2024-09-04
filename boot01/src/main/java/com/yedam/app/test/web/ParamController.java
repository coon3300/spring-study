package com.yedam.app.test.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.serviece.EmpVO;

@Controller
public class ParamController {
	// Content-type : application/x-www-form-urlencoded
	// QueryString(질의문자열) : key=value&key=value...
	// Method : 상관없음
	
	// QueryString => 커맨드 객체 (어노테이션 X, 객체)
	
	@RequestMapping(path="comobj", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t employee_id :" + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	
	@RequestMapping(path="reqparam", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String requestParam(
			@RequestParam 	Integer employeeId,
							String lastName,
			@RequestParam(	name="message",
							defaultValue = "No message",
							required = true) String msg) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t employee_id :" + employeeId;
		result += "\t last_name : " + lastName;
		result += "\t message : " + msg;
		return result;
	}
	
	
	// Content-Type : application/json
	// JSON 포맷 : @RequestBody, 배열 or 객체
	// Method : POST, PUT
	@PostMapping("resbody")
	@ResponseBody
	public String requestBody(@RequestBody EmpVO empVO) {
		
		String result = "Path : /resbody \n";
		result += "\t employee_id :" + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	/*
	 * boomerang post json 
	{
	    "employeeId" : "9876",
	    "lastName" : "Hong"
	}
	 */
	
	
	@PostMapping("resbodyList")
	@ResponseBody
	public String requestBodyList(@RequestBody List<EmpVO> list) {
		
		String result = "Path : /resbodyList \n";
		
		for(EmpVO empVO : list) {
			result += "\t employee_id :" + empVO.getEmployeeId();
			result += "\t last_name : " + empVO.getLastName();
			result += "\n";
		}
		
		/*
		list.forEach(ele -> {
			result += "\t employee_id :" + ele.getEmployeeId();
			result += "\t last_name : " + ele.getLastName();
			result += "\n";
		});
		
		 * 
		 */
		return result;
	}
	/*
	 * boomerang post json
	[
	    {
	    "employeeId" : "9876",
	    "lastName" : "Hong"
	    }
	]
	 */
	
	
//	@PathVariable : 경로에 값을 포함하는 방식, 단일 값
//	Method는 상관없음
//	Content-type도 상관없음
	@RequestMapping("path/{id}") // id=yk => paht/yk
	@ResponseBody
	public String pathVariable(@PathVariable String id) {
		String result = "";
		result += "path : /path/{id} \n";
		result += "\t id : " + id;
		return result;
	}

}
