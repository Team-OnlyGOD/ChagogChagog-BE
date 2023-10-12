package com.onlygod.chagogchagogbe.domain.product.presentation;

import com.onlygod.chagogchagogbe.domain.product.presentation.dto.request.CreateIncomingProductRequest;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.request.CreateNewProductRequest;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.request.CreateOutgoingProductRequest;
import com.onlygod.chagogchagogbe.domain.product.service.CreateIncomingProductService;
import com.onlygod.chagogchagogbe.domain.product.service.CreateNewProductService;
import com.onlygod.chagogchagogbe.domain.product.service.CreateOutgoingProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {

    private final CreateNewProductService createNewProductService;
    private final CreateIncomingProductService createIncomingProductService;
    private final CreateOutgoingProductService createOutgoingProductService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    public void createNewProduct(@RequestBody @Valid CreateNewProductRequest request) {
        createNewProductService.execute(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/incoming/{product-id}")
    public void createIncomingProduct(
            @PathVariable("product-id") Long productId,
            @RequestBody @Valid CreateIncomingProductRequest request
    ) {
        createIncomingProductService.execute(productId, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/outgoing/{product-id}")
    public void createOutgoingProduct(
            @PathVariable("product-id") Long productId,
            @RequestBody @Valid CreateOutgoingProductRequest request
    ) {
        createOutgoingProductService.execute(productId, request);
    }
}
