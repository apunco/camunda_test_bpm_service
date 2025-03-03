package worker;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.springframework.stereotype.Component;

@ExternalTaskSubscription(topicName = "${camunda-process.calculate_risk_worker}")
@RequiredArgsConstructor
@Component
public class CalculateRiskWorker {

    
}
