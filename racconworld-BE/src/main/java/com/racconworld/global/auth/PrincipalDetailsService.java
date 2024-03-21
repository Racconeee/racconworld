package com.racconworld.global.auth;

import com.racconworld.domain.user.User;
import com.racconworld.domain.user.UserRepository;
import com.racconworld.global.exception.MemberException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.racconworld.global.exception.MemberErrorCode.MEMBER_NOT_FOUND_ERROR;


@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userEntity = userRepository.findByUsername(username).orElseThrow( () -> new MemberException(MEMBER_NOT_FOUND_ERROR));

        log.info("userEntity login : {} " , userEntity);
        log.info("userEntity login : {} " , userEntity.getUsername());
        log.info("userEntity login : {} " , userEntity.getPassword());

        return new PrincipalDetails(userEntity);


    }
}
