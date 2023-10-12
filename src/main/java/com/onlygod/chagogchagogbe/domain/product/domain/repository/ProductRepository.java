package com.onlygod.chagogchagogbe.domain.product.domain.repository;

import com.onlygod.chagogchagogbe.domain.product.domain.IncomingProduct;
import com.onlygod.chagogchagogbe.domain.product.domain.OutgoingProduct;
import com.onlygod.chagogchagogbe.domain.product.domain.Product;
import com.onlygod.chagogchagogbe.domain.product.domain.enums.ABCType;
import com.onlygod.chagogchagogbe.domain.product.domain.enums.SaleStatus;
import com.onlygod.chagogchagogbe.domain.product.domain.repository.vo.QQueryABCTypeProductsVO;
import com.onlygod.chagogchagogbe.domain.product.domain.repository.vo.QQueryProductsVO;
import com.onlygod.chagogchagogbe.domain.product.domain.repository.vo.QueryABCTypeProductsVO;
import com.onlygod.chagogchagogbe.domain.product.domain.repository.vo.QueryProductsVO;
import com.onlygod.chagogchagogbe.domain.product.exception.ProductNotFoundException;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.onlygod.chagogchagogbe.domain.product.domain.QIncomingProduct.incomingProduct;
import static com.onlygod.chagogchagogbe.domain.product.domain.QOutgoingProduct.outgoingProduct;
import static com.onlygod.chagogchagogbe.domain.product.domain.QProduct.product;
import static com.onlygod.chagogchagogbe.domain.user.domain.QUser.user;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.sum;
import static com.querydsl.jpa.JPAExpressions.select;

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

    public List<QueryProductsVO> queryProductsByConditions(String name, Long userId) {
        return queryFactory
                .select(
                        new QQueryProductsVO(
                                product.id,
                                product.saleStatus,
                                product.name,
                                product.count,
                                product.safetyCount,
                                product.count,
                                product.count,
                                incomingProduct.createdAt
                        )
                )
                .from(product)
                .join(product.incomingProducts, incomingProduct)
                .on(
                        incomingProduct.product.id.eq(product.id),
                        incomingProduct.createdAt.eq(
                                select(incomingProduct.createdAt.min())
                                        .from(incomingProduct)
                                        .where(incomingProduct.product.id.eq(product.id))
                        )
                )
                .where(
                        eqName(name),
                        product.user.id.eq(userId)
                )
                .fetch();
    }

    public List<QueryABCTypeProductsVO> queryABCTypeProductsByConditions(String name, ABCType abcType, Long userId) {
        return queryFactory
                .selectFrom(product)
                .join(product.outgoingProducts, outgoingProduct)
                .groupBy(
                        product.id,
                        product.name,
                        outgoingProduct.count
                )
                .where(
                        eqName(name),
                        product.abcType.eq(abcType),
                        product.user.id.eq(userId)
                )
                .transform(
                        groupBy(product.id)
                                .list(
                                        new QQueryABCTypeProductsVO(
                                                product.id,
                                                product.name,
                                                sum(outgoingProduct.count)
                                        )
                                )
                );
    }

    //==conditions==//

    public BooleanExpression eqName(String name) {
        return name == null ? null : product.name.eq(name);
    }
}
