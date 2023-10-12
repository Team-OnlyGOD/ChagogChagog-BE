package com.onlygod.chagogchagogbe.domain.product.service;

import com.onlygod.chagogchagogbe.domain.product.domain.IncomingProduct;
import com.onlygod.chagogchagogbe.domain.product.domain.OutgoingProduct;
import com.onlygod.chagogchagogbe.domain.product.domain.Product;
import com.onlygod.chagogchagogbe.domain.product.domain.enums.ABCType;
import com.onlygod.chagogchagogbe.domain.product.domain.repository.ProductRepository;
import com.onlygod.chagogchagogbe.domain.user.domain.User;
import com.onlygod.chagogchagogbe.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class DivideABCTypeService {

    private final UserFacade userFacade;
    private final ProductRepository productRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();
        List<Product> products = productRepository.queryProductByUserId(user.getId());

        Map<Long, Integer> map = new HashMap<>();
        int totalAnnualCost = 0;
        for (Product product : products) {
            List<OutgoingProduct> outgoingProducts = productRepository.queryOutgoingProductByProductId(product.getId());

            int outgoingCount = outgoingProducts.stream()
                    .mapToInt(OutgoingProduct::getCount)
                    .sum();

            int annualCost = outgoingCount * product.getIncomingPrice().intValue();
            map.put(product.getId(), annualCost);
            totalAnnualCost += annualCost;
        }

        List<Map.Entry<Long, Integer>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort((e1, e2) -> -Integer.compare(e1.getValue(), e2.getValue()));

        int cumulativeAnnualCost = 0;
        List<Long> aProduct = new ArrayList<>();
        List<Long> bProduct = new ArrayList<>();
        List<Long> cProduct = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : sortedEntries) {
            cumulativeAnnualCost += entry.getValue();
            float percent = cumulativeAnnualCost / (float) totalAnnualCost;
            if (0 <= percent && percent <= 0.8) {
                aProduct.add(entry.getKey());
            } else if (0.8 <= percent && percent <= 0.95) {
                bProduct.add(entry.getKey());
            } else if (0.95 <= percent && percent <= 1) {
                cProduct.add(entry.getKey());
            }
        }

        productRepository.updateABCTypeByProductIdIn(ABCType.A, aProduct);
        productRepository.updateABCTypeByProductIdIn(ABCType.B, bProduct);
        productRepository.updateABCTypeByProductIdIn(ABCType.C, cProduct);
    }
}
