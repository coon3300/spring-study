package com.yedam.app.log.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.log.service.LogService;

@Controller
public class LogController {
    private final LogService logService;
    
    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/log")
    public String getLog(Model model) {
    	Map<String, Map<String, Integer>> stats = logService.getLog();
//        if (stats.isEmpty()) {
        if (stats.get("methods").isEmpty() && stats.get("services").isEmpty()) {
            model.addAttribute("noData", true);
        } else {
//            model.addAttribute("logStats", stats);
            model.addAttribute("methodStats", stats.get("methods"));
            model.addAttribute("serviceStats", stats.get("services"));            
        }
        return "log";
    }
}
