package com.racconworld.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode{

    INVALID_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, 400, "올바르지 않은 파라미터입니다."),
    INVALID_SERVER_ERROR(HttpStatus.BAD_REQUEST, 400, "서버 오류가 발생했습니다. 잠시 후 이용해주세요"),
    INVALID_LOGIN_ERROR(HttpStatus.BAD_REQUEST, 400, "유요하지않는 이메일,비밀번호 정보입니다. 다시 입력해주세요");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

}
