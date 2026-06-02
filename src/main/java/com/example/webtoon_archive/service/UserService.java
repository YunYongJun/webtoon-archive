package com.example.webtoon_archive.service;

import com.example.webtoon_archive.domain.User;
import com.example.webtoon_archive.dto.UserSignupRequest;
import com.example.webtoon_archive.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // SecurityConfig에서 만든 암호화 부품 주입

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
}
