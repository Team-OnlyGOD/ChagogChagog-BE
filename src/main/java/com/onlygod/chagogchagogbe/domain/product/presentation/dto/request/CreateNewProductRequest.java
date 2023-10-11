package com.onlygod.chagogchagogbe.domain.product.presentation.dto.request;

import com.onlygod.chagogchagogbe.domain.product.domain.enums.ABCType;
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
    private Long price;
}
