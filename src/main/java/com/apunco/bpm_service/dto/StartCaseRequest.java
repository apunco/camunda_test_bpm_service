package com.apunco.bpm_service.dto;

public record StartCaseRequest(
    String caseId,
    CaseType caseType
) {

}
