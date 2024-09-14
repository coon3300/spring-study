package com.yedam.app.log.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.yedam.app.log.service.LogService;

@Service
public class LogServiceImpl implements LogService{
    private static final String LOG_FILE_PATH = "./logs/boot01_application.log";
    private static final Pattern LOG_PATTERN = Pattern.compile("Finished (\\w+) in (\\w+) \\((\\d+)ms\\)");
    private static final List<String> EXCLUDED_METHODS = Arrays.asList("getLog", "SomeOtherMethod");
    private static final List<String> EXCLUDED_SERVICES = Arrays.asList("LogServiceImpl", "SomeOtherService");

    @Override
    public Map<String, Map<String, Integer>> getLog() {
        Map<String, Integer> methodCounts = new HashMap<>();
        Map<String, Integer> serviceCounts = new HashMap<>();


        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = LOG_PATTERN.matcher(line);
                if (matcher.find()) {
                    String method = matcher.group(1);
                    String service = matcher.group(2);
//                    if (!EXCLUDED_METHODS.contains(method)) {
                    if (!EXCLUDED_METHODS.contains(method) && !EXCLUDED_SERVICES.contains(service)) {
                        methodCounts.merge(method, 1, Integer::sum);
                        serviceCounts.merge(service, 1, Integer::sum);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }        

        Map<String, Map<String, Integer>> result = new HashMap<>();
        result.put("methods", methodCounts);
        result.put("services", serviceCounts);
        
        return result;        
        
    }
}
