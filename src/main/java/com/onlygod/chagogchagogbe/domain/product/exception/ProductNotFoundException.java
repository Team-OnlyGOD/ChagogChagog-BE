package com.onlygod.chagogchagogbe.domain.product.exception;

import com.onlygod.chagogchagogbe.global.error.exception.CustomException;
import com.onlygod.chagogchagogbe.global.error.exception.ErrorCode;

public class ProductNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new ProductNotFoundException();

    private ProductNotFoundException() {
        super(ErrorCode.PRODUCT_NOT_FOUND);
    }
}
