package com.onlygod.chagogchagogbe.domain.product.domain.repository;

import com.onlygod.chagogchagogbe.domain.product.domain.IncomingProduct;
import com.onlygod.chagogchagogbe.domain.product.domain.OutgoingProduct;
import com.onlygod.chagogchagogbe.domain.product.domain.Product;
import com.onlygod.chagogchagogbe.domain.product.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final IncomingProductJpaRepository incomingProductJpaRepository;
    private final OutgoingProductJpaRepository outgoingProductJpaRepository;

    public Product saveProduct(Product product) {
        return productJpaRepository.save(product);
    }

    public IncomingProduct saveIncomingProduct(IncomingProduct incomingProduct) {
        return incomingProductJpaRepository.save(incomingProduct);
    }

    public Product queryProductById(Long id) {
        return productJpaRepository.findById(id)
                .orElseThrow(() -> ProductNotFoundException.EXCEPTION);
    }

    public OutgoingProduct saveOutgoingProduct(OutgoingProduct outgoingProduct) {
        return outgoingProductJpaRepository.save(outgoingProduct);
    }
}
