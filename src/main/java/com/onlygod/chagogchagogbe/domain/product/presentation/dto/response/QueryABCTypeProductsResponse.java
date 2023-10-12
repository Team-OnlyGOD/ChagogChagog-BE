package com.onlygod.chagogchagogbe.domain.product.presentation.dto.response;

import com.onlygod.chagogchagogbe.domain.product.domain.repository.vo.QueryABCTypeProductsVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryABCTypeProductsResponse {
    private final List<QueryABCTypeProductsVO> aProducts;
    private final List<QueryABCTypeProductsVO> bProducts;
    private final List<QueryABCTypeProductsVO> cProducts;
}
