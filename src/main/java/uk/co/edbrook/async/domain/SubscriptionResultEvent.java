package uk.co.edbrook.async.domain;

import org.springframework.context.ApplicationEvent;

public class SubscriptionResultEvent extends ApplicationEvent {
    public SubscriptionResultEvent(SubscriptionResponse source) {
        super(source);
    }

    @Override
    public SubscriptionResponse getSource() {
        return (SubscriptionResponse) super.getSource();
    }
}
