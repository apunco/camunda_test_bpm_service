package com.apunco.bpm_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "basic-auth")
public class BasicAuthConfigurationProperties {
    private String username;
    private String password;
}
