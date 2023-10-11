package com.onlygod.chagogchagogbe.domain.product.domain.repository;

import com.onlygod.chagogchagogbe.domain.product.domain.IncomingProduct;
import com.onlygod.chagogchagogbe.domain.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final IncomingProductJpaRepository incomingProductJpaRepository;

    public Product saveProduct(Product product) {
        return productJpaRepository.save(product);
    }

    public IncomingProduct saveIncomingProduct(IncomingProduct incomingProduct) {
        return incomingProductJpaRepository.save(incomingProduct);
    }
}
