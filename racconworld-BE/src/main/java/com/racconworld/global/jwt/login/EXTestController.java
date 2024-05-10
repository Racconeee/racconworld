package com.racconworld.global.jwt.login;


import com.racconworld.domain.user.Role;
import com.racconworld.domain.user.User;
import com.racconworld.domain.user.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/admin")
@Tag(name = "admin Controller", description = "admin 회원가입")
public class EXTestController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    //테스트  - admin
    @GetMapping("/good")
    public String good(){
        return "Admin 통과";
    }
//    테스트  - admin 회원가입
    @PostMapping("/signup_admin")
    public String signup(@RequestBody LoginDto dto){
        User user = new User(dto.getUsername(), bCryptPasswordEncoder.encode(dto.getPassword()), Role.ADMIN);

        userRepository.save(user);

        return "Admin 회원가입";
    }
//    @PostMapping("/signup_user")
//    public String signup2(@RequestBody LoginDto dto){
//        User user = new User(dto.getUsername(), bCryptPasswordEncoder.encode(dto.getPassword()),Role.USER);
//
//        userRepository.save(user);
//
//        return "Admin 회원가입";
//    }
}
