package com.apunco.bpm_service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "case-service-client", url = "$case-service.url")
public interface CaseServiceClient {
}
