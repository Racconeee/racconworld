package com.racconworld.global.jwt.login;

import lombok.Data;
import lombok.Getter;

@Data
@Getter

public class LoginDto {

    private String username;
    private String password;
}
