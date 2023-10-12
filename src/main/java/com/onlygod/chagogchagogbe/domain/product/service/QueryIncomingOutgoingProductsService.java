package com.onlygod.chagogchagogbe.domain.product.service;

import com.onlygod.chagogchagogbe.domain.product.domain.repository.ProductRepository;
import com.onlygod.chagogchagogbe.domain.product.domain.repository.vo.QueryIncomingOutgoingProductsVO;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.response.QueryIncomingOutgoingProductsResponse;
import com.onlygod.chagogchagogbe.domain.user.domain.User;
import com.onlygod.chagogchagogbe.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryIncomingOutgoingProductsService {

    private final ProductRepository productRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryIncomingOutgoingProductsResponse execute(Long productId) {
        User user = userFacade.getCurrentUser();

        List<QueryIncomingOutgoingProductsVO> incomingProducts =
                productRepository.queryIncomingProducts(productId, user.getId()).stream()
                .map(product -> product.setCategory("incoming"))
                .toList();
        List<QueryIncomingOutgoingProductsVO> outgoingProducts =
                productRepository.queryOutgoingProducts(productId, user.getId()).stream()
                        .map(product -> product.setCategory("outgoing"))
                        .toList();

        List<QueryIncomingOutgoingProductsVO> incomingOutgoingProducts = new ArrayList<>();
        incomingOutgoingProducts.addAll(incomingProducts);
        incomingOutgoingProducts.addAll(outgoingProducts);
        incomingOutgoingProducts.sort((p1, p2) -> -p1.getCreatedAt().compareTo(p2.getCreatedAt()));

        return new QueryIncomingOutgoingProductsResponse(incomingOutgoingProducts);
    }
}
