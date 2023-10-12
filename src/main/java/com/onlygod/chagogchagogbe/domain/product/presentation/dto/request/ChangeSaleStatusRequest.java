package com.onlygod.chagogchagogbe.domain.product.presentation.dto.request;

import com.onlygod.chagogchagogbe.domain.product.domain.enums.SaleStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeSaleStatusRequest {

    @NotNull
    private SaleStatus saleStatus;

    private List<Long> productIds;
}
