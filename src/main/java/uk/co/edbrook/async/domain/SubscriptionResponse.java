package uk.co.edbrook.async.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class SubscriptionResponse {
    UUID requestId;
    String subscriptionId;
}
