package com.onlygod.chagogchagogbe.domain.user.exception;

import com.onlygod.chagogchagogbe.global.error.exception.CustomException;
import com.onlygod.chagogchagogbe.global.error.exception.ErrorCode;

public class InvalidPasswordException extends CustomException {

    public static final CustomException EXCEPTION = new InvalidPasswordException();

    private InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}
