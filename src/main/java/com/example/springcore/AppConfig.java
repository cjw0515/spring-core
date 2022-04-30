package com.example.springcore;

import com.example.springcore.service.TestServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public TestServiceImpl testService(){
        return new TestServiceImpl();
    }
}
