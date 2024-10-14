package com.sjc.app.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
	@GetMapping("/")
	public String root() {
		return "redirect:main";
	}

	@GetMapping("main")
	public void main() {}
	
	@GetMapping("all")
	public void all() {}
	
	@GetMapping("user")
	public void user() {}
	
	@GetMapping("admin")
	public void admin() {}
	
    @GetMapping("/logins")
    public void logins() {}
}
