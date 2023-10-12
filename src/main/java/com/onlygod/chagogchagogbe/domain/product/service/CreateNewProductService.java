package com.onlygod.chagogchagogbe.domain.product.service;

import com.onlygod.chagogchagogbe.domain.product.domain.IncomingProduct;
import com.onlygod.chagogchagogbe.domain.product.domain.Product;
import com.onlygod.chagogchagogbe.domain.product.domain.enums.SaleStatus;
import com.onlygod.chagogchagogbe.domain.product.domain.repository.ProductRepository;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.request.CreateNewProductRequest;
import com.onlygod.chagogchagogbe.domain.user.domain.User;
import com.onlygod.chagogchagogbe.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateNewProductService {

    private final ProductRepository productRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(CreateNewProductRequest request) {
        User user = userFacade.getCurrentUser();
        Product product = productRepository.saveProduct(
                Product.builder()
                        .name(request.getName())
                        .count(request.getCount())
                        .safetyCount(request.getSafetyCount())
                        .incomingPrice(request.getIncomingPrice())
                        .outgoingPrice(request.getOutgoingPrice())
                        .saleStatus(SaleStatus.ON_SALE)
                        .user(user)
                        .build()
        );

        productRepository.saveIncomingProduct(
                IncomingProduct.builder()
                        .count(request.getCount())
                        .product(product)
                        .build()
        );
    }
}
