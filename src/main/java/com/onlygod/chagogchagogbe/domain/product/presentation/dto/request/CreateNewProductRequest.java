package com.onlygod.chagogchagogbe.domain.product.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateNewProductRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer count;

    @NotNull
    private Integer safetyCount;

    @NotNull
    private Long incomingPrice;

    @NotNull
    private Long outgoingPrice;

    @NotBlank
    private String imageUrl;
}
