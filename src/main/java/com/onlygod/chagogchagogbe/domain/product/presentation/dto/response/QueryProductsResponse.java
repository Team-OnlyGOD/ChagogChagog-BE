package com.onlygod.chagogchagogbe.domain.product.presentation.dto.response;

import com.onlygod.chagogchagogbe.domain.product.domain.repository.vo.QueryProductsVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryProductsResponse {
    private final List<QueryProductsVO> products;
}
