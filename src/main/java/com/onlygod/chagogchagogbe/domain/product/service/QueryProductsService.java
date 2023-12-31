package com.onlygod.chagogchagogbe.domain.product.service;

import com.onlygod.chagogchagogbe.domain.product.domain.repository.ProductRepository;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.response.QueryProductsResponse;
import com.onlygod.chagogchagogbe.domain.user.domain.User;
import com.onlygod.chagogchagogbe.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryProductsService {

    private final ProductRepository productRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryProductsResponse execute(String name) {
        User user = userFacade.getCurrentUser();
        return new QueryProductsResponse(productRepository.queryProductsByConditions(name, user.getId()));
    }
}
