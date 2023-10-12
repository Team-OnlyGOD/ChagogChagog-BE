package com.onlygod.chagogchagogbe.domain.product.service;

import com.onlygod.chagogchagogbe.domain.product.domain.repository.ProductRepository;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.request.ChangeSaleStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChangeSaleStatusService {

    private final ProductRepository productRepository;

    @Transactional
    public void execute(ChangeSaleStatusRequest request) {
        productRepository.updateSaleStatusByIdIn(request.getSaleStatus(), request.getProductIds());
    }
}
