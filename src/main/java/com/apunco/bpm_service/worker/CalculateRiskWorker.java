package com.apunco.bpm_service.worker;

import com.apunco.bpm_service.service.CaseService;
import com.apunco.openapi.model.CaseType;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.util.Map;

@ExternalTaskSubscription(topicName = "${camunda-process.calculate_risk_worker}")
@RequiredArgsConstructor
@Component
public class CalculateRiskWorker {
    private final CaseService caseService;

    public void executeWorker(ExternalTask externalTask, ExternalTaskService externalTaskService){
        String caseType = externalTask.getVariable("caseType");
        String calculatedRisk = caseService.calculateRisk(CaseType.fromValue(caseType));
        externalTaskService.complete(externalTask,
                        Map.of("calculatedRisk", calculatedRisk));

    }
}
