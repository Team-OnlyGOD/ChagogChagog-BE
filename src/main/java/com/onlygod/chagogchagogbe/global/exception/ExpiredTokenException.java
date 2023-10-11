package com.onlygod.chagogchagogbe.global.exception;

import com.onlygod.chagogchagogbe.global.error.exception.CustomException;
import com.onlygod.chagogchagogbe.global.error.exception.ErrorCode;

public class ExpiredTokenException extends CustomException {
    public static final CustomException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
