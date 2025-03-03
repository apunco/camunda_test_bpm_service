package com.apunco.bpm_service.config;

import org.camunda.community.rest.client.springboot.CamundaAutodeploymentProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CamundaConfig {

    @Bean
    @Primary
    public CamundaAutodeploymentProperties camundaAutodeploymentProperties(){
        var props = new CamundaAutodeploymentProperties();
        props.setEnabled(true);
        props.setBpmnResources("classpath:/camunda/*.bpmn");
        props.setDmnResources("classpath:/camunda/**/*.dmn");
        props.setFormResources("classpath:/camunda/**/*.form");
        return props;
    }

}
