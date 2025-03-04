package com.apunco.bpm_service.client;

import com.apunco.bpm_service.config.CaseServiceClientConfig;
import com.apunco.openapi.model.CalculateRiskResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "case-service-client", url = "${case-service.url}", configuration = CaseServiceClientConfig.class)
public interface CaseServiceClient {

    @GetMapping("/risk/calculate")
    CalculateRiskResponse calculateRiskResponse(@RequestParam("caseType") String caseType);
}
