package com.onlygod.chagogchagogbe.domain.product.presentation.dto.response;

import com.onlygod.chagogchagogbe.domain.product.domain.repository.vo.QueryIncomingOutgoingProductsVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryIncomingOutgoingProductsResponse {
    private final List<QueryIncomingOutgoingProductsVO> incomingOutgoingProducts;
}
