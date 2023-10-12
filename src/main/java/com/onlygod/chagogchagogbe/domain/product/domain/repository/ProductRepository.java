package com.onlygod.chagogchagogbe.domain.product.domain.repository;

import com.onlygod.chagogchagogbe.domain.product.domain.IncomingProduct;
import com.onlygod.chagogchagogbe.domain.product.domain.OutgoingProduct;
import com.onlygod.chagogchagogbe.domain.product.domain.Product;
import com.onlygod.chagogchagogbe.domain.product.domain.enums.ABCType;
import com.onlygod.chagogchagogbe.domain.product.domain.enums.SaleStatus;
import com.onlygod.chagogchagogbe.domain.product.exception.ProductNotFoundException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.onlygod.chagogchagogbe.domain.product.domain.QOutgoingProduct.outgoingProduct;
import static com.onlygod.chagogchagogbe.domain.product.domain.QProduct.product;
import static com.onlygod.chagogchagogbe.domain.user.domain.QUser.user;

@RequiredArgsConstructor
@Repository
public class ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final IncomingProductJpaRepository incomingProductJpaRepository;
    private final OutgoingProductJpaRepository outgoingProductJpaRepository;
    private final JPAQueryFactory queryFactory;

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

    public List<Product> queryProductByUserId(Long userId) {
        return queryFactory
                .selectFrom(product)
                .join(product.user, user)
                .where(user.id.eq(userId))
                .fetch();
    }

    public List<OutgoingProduct> queryOutgoingProductByProductId(Long productId) {
        return queryFactory
                .selectFrom(outgoingProduct)
                .join(outgoingProduct.product, product)
                .where(product.id.eq(productId))
                .fetch();
    }

    public void updateABCTypeByProductIdIn(ABCType abcType, List<Long> productIds) {
        queryFactory
                .update(product)
                .set(product.abcType, abcType)
                .where(product.id.in(productIds))
                .execute();
    }

    public void updateSaleStatusByIdIn(SaleStatus saleStatus, List<Long> productIds) {
        queryFactory
                .update(product)
                .set(product.saleStatus, saleStatus)
                .where(product.id.in(productIds))
                .execute();
    }
}
