package com.yedam.app.dept.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class DeptConfig {

    @Bean
    CountedAspect countedAspect(MeterRegistry registry) {
		return new CountedAspect(registry);
	}
}
