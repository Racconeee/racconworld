package com.racconworld.global.jwt.login;


import com.racconworld.domain.user.Role;
import com.racconworld.domain.user.User;
import com.racconworld.domain.user.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "admin Controller", description = "admin 회원가입")
public class AdminController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${signup.Token}")
    private String token;

//    테스트  - admin 회원가입
    @Operation(summary = "admin 계정 회원가입",
            description = "Token의 값이 서버의 값과 일치해야 회원가입이 성공한다.")
    @PostMapping("/signup_admin")
    public String signup(@Valid @RequestBody LoginDto dto){
        if(token.equals(dto.getSignup_Token())){
            User user = new User(dto.getUsername(), bCryptPasswordEncoder.encode(dto.getPassword()), Role.ADMIN);
            userRepository.save(user);
            return "Admin 회원가입";
        }
        return "Token 값이 일치하지않아 회원가입이 실패되었습니다.";
    }

}
