package com.apunco.bpm_service.service;

import com.apunco.bpm_service.dto.StartCaseRequest;
import com.apunco.openapi.model.StartCaseResponse;
import lombok.RequiredArgsConstructor;
import org.camunda.community.rest.client.api.ProcessDefinitionApi;
import org.camunda.community.rest.client.dto.ProcessInstanceWithVariablesDto;
import org.camunda.community.rest.client.dto.StartProcessInstanceDto;
import org.camunda.community.rest.client.dto.VariableValueDto;
import org.camunda.community.rest.client.invoker.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CamundaService {

    private static final String CASE_ID = "caseId";
    private static final String CASE_TYPE = "caseType";

    @Value("${camunda-process.case-validation-key}")
    private String caseValidationKey;

    private final ProcessDefinitionApi processDefinitionApi;

    public StartCaseResponse startCaseEvaluationProcess(com.apunco.bpm_service.dto.StartCaseRequest startCaseRequest){
        ProcessInstanceWithVariablesDto processInstanceWithVariablesDto;

        StartProcessInstanceDto startProcessInstanceDto = initStartProcessDto(startCaseRequest);
        try {
            processInstanceWithVariablesDto = processDefinitionApi
                    .startProcessInstanceByKey(caseValidationKey, startProcessInstanceDto);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        StartCaseResponse startCaseResponse = new StartCaseResponse();
        startCaseResponse.setProcessInstanceId(processInstanceWithVariablesDto.getId());
        return startCaseResponse;
    }

    private StartProcessInstanceDto initStartProcessDto(StartCaseRequest startCaseRequest){
        var caseId = new VariableValueDto();
        caseId.setValue(startCaseRequest.caseId());

        var caseType = new VariableValueDto();
        caseType.setValue(startCaseRequest.caseType());

        var variables = Map.of(
                CASE_ID, caseId,
                CASE_TYPE, caseType
        );

        StartProcessInstanceDto startProcessInstanceDto = new StartProcessInstanceDto();
        startProcessInstanceDto.setVariables(variables);

        return startProcessInstanceDto;
    }
}
