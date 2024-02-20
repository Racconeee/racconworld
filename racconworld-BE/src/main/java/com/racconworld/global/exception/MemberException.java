package com.racconworld.global.exception;

import lombok.Getter;

@Getter
public class MemberException extends CustomException{


    private final MemberErrorCode memberErrorCode;

    public MemberException(MemberErrorCode memberErrorCode) {
        super(memberErrorCode.getMessage());
        this.memberErrorCode = memberErrorCode;
    }

    public MemberErrorCode getMemberErrorCode() {
        return memberErrorCode;
    }
}
