package com.onlygod.chagogchagogbe.domain.product.exception;

import com.onlygod.chagogchagogbe.global.error.exception.CustomException;
import com.onlygod.chagogchagogbe.global.error.exception.ErrorCode;

public class ProductCountNotEnoughException extends CustomException {

    public static final CustomException EXCEPTION = new ProductCountNotEnoughException();

    private ProductCountNotEnoughException() {
        super(ErrorCode.PRODUCT_COUNT_NOT_ENOUGH);
    }
}
