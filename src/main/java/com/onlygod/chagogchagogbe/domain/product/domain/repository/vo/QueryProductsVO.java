package com.onlygod.chagogchagogbe.domain.product.domain.repository.vo;

import com.onlygod.chagogchagogbe.domain.product.domain.enums.SaleStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class QueryProductsVO {
    private final Long productId;
    private final SaleStatus saleStatus;
    private final String name;
    private final Integer count;
    private final Integer safetyCount;
    private final Integer normalCount;
    private final Integer badCount;
    private final LocalDate createdAt;

    @QueryProjection
    public QueryProductsVO(Long productId, SaleStatus saleStatus, String name, Integer count, Integer safetyCount,
                           Integer normalCount, Integer badCount, LocalDateTime createdAt) {
        this.productId = productId;
        this.saleStatus = saleStatus;
        this.name = name;
        this.count = count;
        this.safetyCount = safetyCount;
        this.normalCount = normalCount;
        this.badCount = badCount;
        this.createdAt = createdAt.toLocalDate();
    }
}
