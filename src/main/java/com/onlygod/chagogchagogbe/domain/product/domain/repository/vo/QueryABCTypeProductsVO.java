package com.onlygod.chagogchagogbe.domain.product.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class QueryABCTypeProductsVO {

    private final Long productId;
    private final String name;
    private final Integer outgoingCount;

    @QueryProjection
    public QueryABCTypeProductsVO(Long productId, String name, Integer outgoingCount) {
        this.productId = productId;
        this.name = name;
        this.outgoingCount = outgoingCount;
    }
}
