package com.onlygod.chagogchagogbe.domain.product.service;

import com.onlygod.chagogchagogbe.domain.product.domain.repository.ProductRepository;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.response.QueryProductsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryProductsService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public QueryProductsResponse execute(String name) {
        return new QueryProductsResponse(productRepository.queryProductsByConditions(name));
    }
}
