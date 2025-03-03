package com.apunco.bpm_service.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.apunco.bpm_service.client")
public class FeignClientConfig {
}
