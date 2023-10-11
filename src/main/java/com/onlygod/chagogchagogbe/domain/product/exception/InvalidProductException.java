package com.onlygod.chagogchagogbe.domain.product.exception;

import com.onlygod.chagogchagogbe.global.error.exception.CustomException;
import com.onlygod.chagogchagogbe.global.error.exception.ErrorCode;

public class InvalidProductException extends CustomException {

    public static final CustomException EXCEPTION = new InvalidProductException();

    private InvalidProductException() {
        super(ErrorCode.PRODUCT_NOT_FOUND);
    }
}
