package com.onlygod.chagogchagogbe.domain.product.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryProductDetailsResponse {
    private final String name;
    private final Long incomingPrice;
    private final Long outgoingPrice;
    private final Integer count;
    private final Integer safetyCount;
    private final Integer normalCount;
    private final Integer badCount;
}
