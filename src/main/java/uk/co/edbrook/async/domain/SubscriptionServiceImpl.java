package uk.co.edbrook.async.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import uk.co.edbrook.async.web.SubscriptionRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService, ApplicationListener<SubscriptionResultEvent> {

    private final RestTemplate restTemplate;
    private final Map<UUID, DeferredResult<String>> responseMap;

    public SubscriptionServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        responseMap = new HashMap<>();
    }

    @Async
    @Override
    public void subscribe(Subscribe subscribe, DeferredResult<String> deferredResult) {
        var requestId = UUID.randomUUID();

        var sr = SubscriptionRequest.builder()
                .requestId(requestId)
                .name(subscribe.getName())
                .email(subscribe.getEmail())
                .build();

        log.info("Request: {}", sr);

        var response = restTemplate.postForEntity("http://localhost:8090/api/subscribe", sr, Void.class);

        log.info("Subscribe Response (1/2): {}", response);

        responseMap.put(requestId, deferredResult);
    }

    @Override
    public void onApplicationEvent(SubscriptionResultEvent event) {
        processIncomingResult(event.getSource());
    }

    private void processIncomingResult(SubscriptionResponse response) {
        log.info("Subscribe Response (2/2): {}", response);
        var requestId = response.getRequestId();
        if (responseMap.containsKey(requestId)) {
            var deferredResult = responseMap.get(requestId);
            deferredResult.setResult("redirect:?subId=" + response.getSubscriptionId());
            responseMap.remove(requestId);
        } else {
            log.error("Failed to link incoming request to requestId: {}", requestId);
        }
    }
}
