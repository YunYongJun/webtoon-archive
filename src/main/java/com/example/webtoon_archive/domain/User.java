package com.example.webtoon_archive.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // MySQL 예약어 user와의 충돌을 피하기 위해 테이블명을 복수형으로 지정
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username; // 로그인용 아이디 (중복 불가)

    @Column(nullable = false, length = 100)
    private String password; // 암호화되어 저장될 비밀번호

    @Column(nullable = false, length = 50)
    private String nickname; // 서비스에서 사용할 닉네임

    public User (String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
