package com.apunco.bpm_service.service;

import com.apunco.bpm_service.client.CaseServiceClient;
import com.apunco.openapi.model.CalculateRiskResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CaseService {

    private final CaseServiceClient caseServiceClient;

    public String calculateRisk(String caseType){
        CalculateRiskResponse response = caseServiceClient.calculateRiskResponse(caseType);
        return response.getRiskScore().getValue();
    }
}
