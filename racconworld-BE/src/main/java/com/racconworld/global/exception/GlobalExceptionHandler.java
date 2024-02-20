package com.racconworld.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    //CustomException 은 간단히 에러 코드 Restapi 로 전달하기
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handlePasswordMismatchException(CustomException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //MemberException class내용을 기반으로 에러 메세지 생성해서 보냄
    //throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS_ERROR); 이런 형식으로 보낸다.
    @ExceptionHandler(MemberException.class)
    public ResponseEntity handleException(MemberException e){
        ErrorCode errorCode = e.getMemberErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus() , errorCode.getCode(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity handleException(CommonException e){
        ErrorCode errorCode = e.getCommonErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus() , errorCode.getCode(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

}
