package com.onlygod.chagogchagogbe.domain.product.service;

import com.onlygod.chagogchagogbe.domain.product.domain.OutgoingProduct;
import com.onlygod.chagogchagogbe.domain.product.domain.Product;
import com.onlygod.chagogchagogbe.domain.product.domain.enums.SaleStatus;
import com.onlygod.chagogchagogbe.domain.product.domain.repository.ProductRepository;
import com.onlygod.chagogchagogbe.domain.product.exception.InvalidProductException;
import com.onlygod.chagogchagogbe.domain.product.exception.ProductCountNotEnoughException;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.request.CreateOutgoingProductRequest;
import com.onlygod.chagogchagogbe.domain.user.domain.User;
import com.onlygod.chagogchagogbe.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateOutgoingProductService {

    private final ProductRepository productRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(Long productId, CreateOutgoingProductRequest request) {
        User user = userFacade.getCurrentUser();
        Product product = productRepository.queryProductById(productId);

        if (!product.getUser().equals(user)) {
            throw InvalidProductException.EXCEPTION;
        }

        if ((product.getCount() - request.getCount()) < 0) {
            throw ProductCountNotEnoughException.EXCEPTION;
        }

        if (product.getCount() - request.getCount() == 0) {
            product.changeSaleStatus(SaleStatus.SOLD_OUT);
        }

        productRepository.saveOutgoingProduct(
                OutgoingProduct.builder()
                        .count(request.getCount())
                        .beforeCount(product.getCount())
                        .product(product)
                        .build()
        );

        productRepository.saveProduct(
                product.subCount(request.getCount())
        );
    }
}
