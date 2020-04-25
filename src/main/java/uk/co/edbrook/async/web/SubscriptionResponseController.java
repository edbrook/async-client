package uk.co.edbrook.async.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.edbrook.async.domain.SubscriptionResponse;
import uk.co.edbrook.async.domain.SubscriptionResultEvent;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SubscriptionResponseController {

    private final ApplicationEventPublisher publisher;

    @PostMapping("/result")
    public void processResponse(@RequestBody SubscriptionResponse response) {
        var event = new SubscriptionResultEvent(response);
        publisher.publishEvent(event);
    }
}
