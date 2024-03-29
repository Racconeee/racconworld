package com.racconworld.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private Role roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
