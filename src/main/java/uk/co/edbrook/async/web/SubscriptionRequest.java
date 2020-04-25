package uk.co.edbrook.async.web;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class SubscriptionRequest {
    UUID requestId;
    String name;
    String email;
}
