package com.example.webtoon_archive.service;

import com.example.webtoon_archive.domain.User;
import com.example.webtoon_archive.dto.UserSignupRequest;
import com.example.webtoon_archive.repository.UserRepository;
import com.example.webtoon_archive.config.JwtProvider;
import com.example.webtoon_archive.dto.UserLoginRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // SecurityConfig에서 만든 암호화 부품 주입
    private final JwtProvider jwtProvider; // JWT 제공자

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    /* 회원가입 기능 (비밀번호 암호화 적용) */
    @Transactional
    public Long signup(UserSignupRequest request) {

        // 1. 아이디 중복 검사
        userRepository.findByUsername(request.getUsername())
        .ifPresent(u -> {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        });

        // 2. 비밀번호 암호화 처리
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 3. 암호화된 비밀번호를 장착하여 회원 객체 생성 후 저장
        User user = new User(request.getUsername(), encodedPassword, request.getNickname());
        User savedUser = userRepository.save(user);

        return savedUser.getId();
    }

    /* 로그인 기능 (비밀번호 대조 및 JWT 토큰 발급) */
    public String login(UserLoginRequest request) {
        // 1. 아이디로 회원 조회 (없으면 예외 발생)
        User user = userRepository.findByUsername(request.getUsername())
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        // 2. 비밀번호 일치 여부 확인 (날것의 비밀번호와 DB에 암호화된 비밀번호 대조)
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 인증 성공 시 JWT 토큰 생성 후 반환
        return jwtProvider.createToken(user.getUsername(), user.getNickname());
    }
}
