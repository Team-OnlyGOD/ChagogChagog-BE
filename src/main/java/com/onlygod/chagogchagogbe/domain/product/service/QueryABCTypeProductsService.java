package com.onlygod.chagogchagogbe.domain.product.service;

import com.onlygod.chagogchagogbe.domain.product.domain.enums.ABCType;
import com.onlygod.chagogchagogbe.domain.product.domain.repository.ProductRepository;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.response.QueryABCTypeProductsResponse;
import com.onlygod.chagogchagogbe.domain.user.domain.User;
import com.onlygod.chagogchagogbe.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryABCTypeProductsService {

    private final ProductRepository productRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryABCTypeProductsResponse execute(String name) {
        User user = userFacade.getCurrentUser();
        return new QueryABCTypeProductsResponse(
                productRepository.queryABCTypeProductsByConditions(name, ABCType.A, user.getId()),
                productRepository.queryABCTypeProductsByConditions(name, ABCType.B, user.getId()),
                productRepository.queryABCTypeProductsByConditions(name, ABCType.C, user.getId())
        );
    }
}
