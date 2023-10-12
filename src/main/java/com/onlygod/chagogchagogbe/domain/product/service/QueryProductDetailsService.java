package com.onlygod.chagogchagogbe.domain.product.service;

import com.onlygod.chagogchagogbe.domain.product.domain.Product;
import com.onlygod.chagogchagogbe.domain.product.domain.repository.ProductRepository;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.response.QueryProductDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryProductDetailsService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public QueryProductDetailsResponse execute(Long productId) {
        Product product = productRepository.queryProductById(productId);

        return QueryProductDetailsResponse.builder()
                .name(product.getName())
                .incomingPrice(product.getIncomingPrice())
                .outgoingPrice(product.getOutgoingPrice())
                .count(product.getCount())
                .safetyCount(product.getSafetyCount())
                .normalCount(product.getCount())
                .badCount(0)
                .build();
    }
}
