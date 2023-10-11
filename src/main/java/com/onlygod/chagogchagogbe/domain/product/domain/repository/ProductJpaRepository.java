package com.onlygod.chagogchagogbe.domain.product.domain.repository;

import com.onlygod.chagogchagogbe.domain.product.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductJpaRepository extends CrudRepository<Product, Long> {
}
