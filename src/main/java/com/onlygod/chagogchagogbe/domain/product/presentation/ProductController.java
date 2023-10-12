package com.onlygod.chagogchagogbe.domain.product.presentation;

import com.onlygod.chagogchagogbe.domain.product.presentation.dto.request.ChangeSaleStatusRequest;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.request.CreateIncomingProductRequest;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.request.CreateNewProductRequest;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.request.CreateOutgoingProductRequest;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.response.QueryABCTypeProductsResponse;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.response.QueryIncomingOutgoingProductsResponse;
import com.onlygod.chagogchagogbe.domain.product.presentation.dto.response.QueryProductsResponse;
import com.onlygod.chagogchagogbe.domain.product.service.ChangeSaleStatusService;
import com.onlygod.chagogchagogbe.domain.product.service.CreateIncomingProductService;
import com.onlygod.chagogchagogbe.domain.product.service.CreateNewProductService;
import com.onlygod.chagogchagogbe.domain.product.service.CreateOutgoingProductService;
import com.onlygod.chagogchagogbe.domain.product.service.DivideABCTypeService;
import com.onlygod.chagogchagogbe.domain.product.service.QueryABCTypeProductsService;
import com.onlygod.chagogchagogbe.domain.product.service.QueryIncomingOutgoingProductsService;
import com.onlygod.chagogchagogbe.domain.product.service.QueryProductsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final DivideABCTypeService divideABCTypeService;
    private final ChangeSaleStatusService changeSaleStatusService;
    private final QueryProductsService queryProductsService;
    private final QueryABCTypeProductsService queryABCTypeProductsService;
    private final QueryIncomingOutgoingProductsService queryIncomingOutgoingProductsService;

    @Operation(description = "새로운 재고 추가시 사용하는 api")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    public void createNewProduct(@RequestBody @Valid CreateNewProductRequest request) {
        createNewProductService.execute(request);
    }

    @Operation(description = "기존 재고 추가시 사용하는 api")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/incoming/{product-id}")
    public void createIncomingProduct(
            @PathVariable("product-id") Long productId,
            @RequestBody @Valid CreateIncomingProductRequest request
    ) {
        createIncomingProductService.execute(productId, request);
    }

    @Operation(description = "출고시 사용하는 api")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/outgoing/{product-id}")
    public void createOutgoingProduct(
            @PathVariable("product-id") Long productId,
            @RequestBody @Valid CreateOutgoingProductRequest request
    ) {
        createOutgoingProductService.execute(productId, request);
    }

    @Operation(description = "새로고침 버튼에 사용하는 api\n이건 사용자의 재고들을 a, b, c등급으로 분리시킵니다.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/abc_type")
    public void divideABCType() {
        divideABCTypeService.execute();
    }

    @Operation(description = "판매상태 변경시 사용하는 api")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/sale_status")
    public void changeSaleStatus(@RequestBody @Valid ChangeSaleStatusRequest request) {
        changeSaleStatusService.execute(request);
    }

    @Operation(description = "재고내역을 상품별로 조회할때 사용하는 api")
    @GetMapping
    public QueryProductsResponse queryProducts(
            @RequestParam(value = "name", required = false) String name
    ) {
        return queryProductsService.execute(name);
    }

    @Operation(description = "재고내역을 수요량별로 조회할때 사용하는 api")
    @GetMapping("/abc_type")
    public QueryABCTypeProductsResponse queryABCTypeProduct(
            @RequestParam(value = "name", required = false) String name
    ) {
        return queryABCTypeProductsService.execute(name);
    }

    @Operation(description = "입출고내역 조회시 사용하는 api\n product_id 파라미터를 넣으면 그 제품에 대한 입출고내역 조회")
    @GetMapping("/incoming_outgoing")
    public QueryIncomingOutgoingProductsResponse queryIncomingOutgoingProducts(
            @RequestParam(value = "product_id", required = false) Long productId
    ) {
        return queryIncomingOutgoingProductsService.execute(productId);
    }
}
