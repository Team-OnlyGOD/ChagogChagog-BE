package com.onlygod.chagogchagogbe.domain.product.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ProductCreatedAtVO {

    private final Long productId;
    private final Integer aDate;
    private final LocalDate createdAt;

    @QueryProjection
    public ProductCreatedAtVO(Long productId, Integer aDate, LocalDateTime createdAt) {
        this.productId = productId;
        this.aDate = aDate;
        this.createdAt = createdAt.toLocalDate();
    }
}
