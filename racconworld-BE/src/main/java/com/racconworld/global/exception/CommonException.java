package com.racconworld.global.exception;

import lombok.Getter;

@Getter
public class CommonException extends CustomException {


    private final CommonErrorCode commonErrorCode;

    public CommonException(CommonErrorCode commonErrorCode) {
        super(commonErrorCode.getMessage());
        this.commonErrorCode = commonErrorCode;
    }

}
