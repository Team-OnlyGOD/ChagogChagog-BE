package com.onlygod.chagogchagogbe.domain.product.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateIncomingProductRequest {

    @NotNull
    private Integer count;
}
