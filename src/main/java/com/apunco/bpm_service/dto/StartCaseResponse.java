package com.apunco.bpm_service.dto;

import lombok.Builder;

@Builder
public record StartCaseResponse(
    String processInstanceId
) {

}
