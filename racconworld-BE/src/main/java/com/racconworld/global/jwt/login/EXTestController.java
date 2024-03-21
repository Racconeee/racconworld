package com.racconworld.global.jwt.login;


import com.racconworld.domain.user.Role;
import com.racconworld.domain.user.User;
import com.racconworld.domain.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EXTestController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    //테스트  - admin
    @GetMapping("/admin/good")
    public String good(){
        return "Admin 통과";
    }
    //테스트  - admin 회원가입
    @PostMapping("/signup")
    public String signup(@RequestBody LoginDto dto){
        User user = new User(dto.getUsername(), bCryptPasswordEncoder.encode(dto.getPassword()));

        user.setRoles(Role.ADMIN);

        userRepository.save(user);

        return "Admin 회원가입";
    }
    @PostMapping("/signupu")
    public String signup2(@RequestBody LoginDto dto){
        User user = new User(dto.getUsername(), bCryptPasswordEncoder.encode(dto.getPassword()));

        user.setRoles(Role.USER);

        userRepository.save(user);

        return "Admin 회원가입";
    }
}
