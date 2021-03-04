package com.sba.ppp.loanforgiveness.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SbaAppConfig{
	
    @Bean(name = "sbaRestTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}