package com.onlygod.chagogchagogbe.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    EXPIRED_TOKEN(401, "Token Expired"),
    INVALID_TOKEN(401, "Invalid Token"),
    INVALID_PASSWORD(401, "Invalid Password"),
    INVALID_PRODUCT(401, "Invalid Product"),

    USER_NOT_FOUND(404, "User Not Found"),
    PRODUCT_NOT_FOUND(404, "Product Not Found"),

    USER_ALREADY_EXISTS(409, "User Already Exists"),
    PRODUCT_COUNT_NOT_ENOUGH(409, "Product Count Not Enough");

    private final Integer status;
    private final String message;
}
