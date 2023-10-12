package com.onlygod.chagogchagogbe.domain.notice.service;

import com.onlygod.chagogchagogbe.domain.notice.domain.Notice;
import com.onlygod.chagogchagogbe.domain.notice.domain.enums.NoticeStatus;
import com.onlygod.chagogchagogbe.domain.notice.domain.repository.NoticeRepository;
import com.onlygod.chagogchagogbe.domain.product.domain.Product;
import com.onlygod.chagogchagogbe.domain.product.domain.repository.ProductRepository;
import com.onlygod.chagogchagogbe.domain.product.domain.repository.vo.ProductCreatedAtVO;
import com.onlygod.chagogchagogbe.domain.product.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderDateSchedulerService {

    private final ProductRepository productRepository;
    private final NoticeRepository noticeRepository;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void execute() {
        List<ProductCreatedAtVO> products = productRepository.queryProductCreatedAt();
        List<Long> afterProductIds = new ArrayList<>();
        products.forEach(
                product -> {
                    if (product.getCreatedAt().plusDays(product.getADate().longValue() - 1).isAfter(LocalDate.now())) {
                        afterProductIds.add(product.getProductId());
                    }
                }
        );

        List<Product> afterProducts = productRepository.queryProductsByIdIn(afterProductIds);
        if (afterProducts.size() != afterProductIds.size()) {
            throw ProductNotFoundException.EXCEPTION;
        }
        List<Notice> notices = afterProducts.stream()
                .map(product -> Notice.builder()
                        .product(product)
                        .noticeStatus(NoticeStatus.ALMOST_ORDER_DATE)
                        .build()
                ).toList();
        noticeRepository.saveAllNotice(notices);
    }
}
