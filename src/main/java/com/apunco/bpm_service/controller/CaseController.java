package com.apunco.bpm_service.controller;

import com.apunco.bpm_service.service.CamundaService;
import com.apunco.openapi.model.StartCaseRequest;
import com.apunco.openapi.model.StartCaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/case")
@RequiredArgsConstructor
public class CaseController {

    private final CamundaService camundaService;

    @PostMapping("/start")
    @Operation(summary = "Start a case process",
    description = "This method starts a case process.",
    responses = {
            @ApiResponse(responseCode = "200", description = "Case process started successfully"),
            @ApiResponse(responseCode = "400", description = "Error response")
    })
    public StartCaseResponse startCase(@RequestBody StartCaseRequest startCaseRequest) {
        return camundaService.startCaseEvaluationProcess(startCaseRequest);
    }

}
