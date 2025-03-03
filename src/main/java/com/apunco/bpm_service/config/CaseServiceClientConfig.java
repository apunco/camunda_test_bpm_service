package com.apunco.bpm_service.config;

import com.apunco.bpm_service.util.SecurityUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class CaseServiceClientConfig implements RequestInterceptor {
    private final String username;
    private final String password;

    public CaseServiceClientConfig(@Value("${case-service.username}") String username,
                                   @Value("${case-service.password}") String password) {
        this.username = username;
        this.password = password;
    }
    @Override
    public void apply(RequestTemplate requestTemplate) {
        SecurityUtils.addBasicAuthHeader(requestTemplate, username, password);
    }
}
