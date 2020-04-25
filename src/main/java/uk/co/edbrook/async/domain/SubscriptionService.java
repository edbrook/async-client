package uk.co.edbrook.async.domain;

import org.springframework.web.context.request.async.DeferredResult;

public interface SubscriptionService {
    void subscribe(Subscribe subscribe, DeferredResult<String> deferredResult);
}
