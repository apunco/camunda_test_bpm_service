package com.apunco.bpm_service.worker;

import com.apunco.bpm_service.service.CaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@ExternalTaskSubscription(topicName = "case-service")
@RequiredArgsConstructor
@Component
@Slf4j
public class CalculateRiskWorker implements ExternalTaskHandler {

    @Value("${worker-config.retries}")
    private int retryCount;

    @Value("${worker-config.retryPeriod}")
    private long retryPeriod;

    private final CaseService caseService;

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService){
        try {
            String caseType = externalTask.getVariable("caseType");
            String calculatedRisk = caseService.calculateRisk(caseType);
            externalTaskService.complete(externalTask,
                    Variables.createVariables().putValue("calculatedRisk", calculatedRisk));
        } catch (Exception e) {
            log.error("{}", e.getMessage());

            if (e.getMessage().contains("Invalid case type provided")){
                externalTaskService.handleBpmnError(externalTask, "invalid-case-provided", e.getMessage());
            }

            externalTaskService.handleFailure(
                    externalTask,
                    e.getMessage(),
                    Arrays.toString(e.getStackTrace()),
                    calculateRetries(externalTask),
                    retryPeriod);
        }
    }

    private int calculateRetries(ExternalTask task){
        log.info(task.getRetries() == null ? "retries are null" : task.getRetries().toString());
        if (task.getRetries() == null){
            return retryCount;
        }
        return task.getRetries() - 1;
    }
}
