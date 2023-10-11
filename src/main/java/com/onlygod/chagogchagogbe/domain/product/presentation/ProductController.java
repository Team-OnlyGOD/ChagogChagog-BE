package com.onlygod.chagogchagogbe.domain.product.presentation;

import com.onlygod.chagogchagogbe.domain.product.presentation.dto.request.CreateNewProductRequest;
import com.onlygod.chagogchagogbe.domain.product.service.CreateNewProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    public void createNewProduct(@RequestBody @Valid CreateNewProductRequest request) {
        createNewProductService.execute(request);
    }
}
