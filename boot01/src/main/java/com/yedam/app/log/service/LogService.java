package com.yedam.app.log.service;

import java.util.Map;

public interface LogService {
//    public Map<String, Integer> getLog();
    public Map<String, Map<String, Integer>> getLog();
}
