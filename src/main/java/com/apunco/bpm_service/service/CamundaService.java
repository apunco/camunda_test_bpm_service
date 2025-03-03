package com.apunco.bpm_service.service;

import com.apunco.bpm_service.dto.StartCaseRequest;
import lombok.RequiredArgsConstructor;
import org.camunda.community.rest.client.api.ProcessDefinitionApi;
import org.camunda.community.rest.client.dto.ProcessInstanceWithVariablesDto;
import org.camunda.community.rest.client.dto.StartProcessInstanceDto;
import org.camunda.community.rest.client.dto.VariableValueDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CamundaService {

    private static final String CASE_ID = "caseId";
    private static final String CASE_TYPE = "caseType";

    private final ProcessDefinitionApi processDefinitionApi;

    public String startCaseEvaluationProcess(StartCaseRequest startCaseRequest){
        ProcessInstanceWithVariablesDto processInstanceWithVariablesDto;

        StartProcessInstanceDto startProcessInstanceDto = initStartProcessDto(startCaseRequest);

        processInstanceDto = processDefinitionApi
                .startProcessInstanceByKey()
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
