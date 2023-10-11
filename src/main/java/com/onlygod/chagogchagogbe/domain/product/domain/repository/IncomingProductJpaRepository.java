package com.onlygod.chagogchagogbe.domain.product.domain.repository;

import com.onlygod.chagogchagogbe.domain.product.domain.IncomingProduct;
import org.springframework.data.repository.CrudRepository;

public interface IncomingProductJpaRepository extends CrudRepository<IncomingProduct, Long> {
}
