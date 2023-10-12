package com.onlygod.chagogchagogbe.domain.product.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class QueryIncomingOutgoingProductsVO {

    private final String name;
    private String category;
    private final LocalDate createdAt;
    private final Integer IncomingOutgoingCount;
    private final Integer beforeIncomingOutgoingCount;

    @QueryProjection
    public QueryIncomingOutgoingProductsVO(String name, LocalDateTime createdAt, Integer incomingOutgoingCount, Integer beforeIncomingOutgoingCount) {
        this.name = name;
        this.createdAt = createdAt.toLocalDate();
        IncomingOutgoingCount = incomingOutgoingCount;
        this.beforeIncomingOutgoingCount = beforeIncomingOutgoingCount;
    }

    public QueryIncomingOutgoingProductsVO setCategory(String category) {
        this.category = category;
        return this;
    }
}
