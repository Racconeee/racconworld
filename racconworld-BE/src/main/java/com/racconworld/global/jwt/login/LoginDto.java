package com.racconworld.global.jwt.login;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Valid
public class LoginDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
