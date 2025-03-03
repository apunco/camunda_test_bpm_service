package com.apunco.bpm_service.util;

import feign.RequestTemplate;

import org.springframework.http.HttpHeaders;

public class SecurityUtils {

    public static final String BASIC = "Basic ";

    private SecurityUtils(){
    }

    public static void addBasicAuthHeader(RequestTemplate requestTemplate, String username, String password) {
        requestTemplate.header(HttpHeaders.AUTHORIZATION, generateBasicAuthHeader(username, password));
    }

    public static String generateBasicAuthHeader(String username, String password){
        return BASIC + encodeBasicAuthorization(username, password);
    }

    private static String encodeBasicAuthorization(String username, String password){
        return java.util.Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }

}

