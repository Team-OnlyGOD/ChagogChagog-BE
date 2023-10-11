package com.onlygod.chagogchagogbe.domain.product.service;

import com.onlygod.chagogchagogbe.domain.product.domain.IncomingProduct;
import com.onlygod.chagogchagogbe.domain.product.domain.Product;
import com.onlygod.chagogchagogbe.domain.product.domain.repository.ProductRepository;
import com.onlygod.chagogchagogbe.domain.product.exception.InvalidProductException;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.request.CreateIncomingProductRequest;
import com.onlygod.chagogchagogbe.domain.user.domain.User;
import com.onlygod.chagogchagogbe.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateIncomingProductService {

    private final ProductRepository productRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(Long productId, CreateIncomingProductRequest request) {
        User user = userFacade.getCurrentUser();
        Product product = productRepository.queryProductById(productId);

        if (!product.getUser().equals(user)) {
            throw InvalidProductException.EXCEPTION;
        }

        productRepository.saveIncomingProduct(
                IncomingProduct.builder()
                        .count(request.getCount())
                        .product(product)
                        .build()
        );

        productRepository.saveProduct(
                product.addCount(request.getCount())
        );
    }
}
