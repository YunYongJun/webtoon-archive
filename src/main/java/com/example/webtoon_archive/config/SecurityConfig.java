package com.example.webtoon_archive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 설정을 활성화
public class SecurityConfig {
    
    // 비밀번호를 안전하게 암호화(해싱)해주는 빈(Bean)을 등록 (BCrypt 알고리즘 사용)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // HTTP 보안 설정을 세부적으로 구성하는 핵심 필터 체인
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        // REST API 서버는 세션을 사용하지 않고 JWT 토큰을 쓸 것이므로 CSRF 방어를 끔
        .csrf(csrf -> csrf.disable())
        // 세션을 생성하지 않도록 스프링 시큐리티에게 지시 (무상태성 유지)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // 각 주소(URL)별 접근 권한을 설정
        .authorizeHttpRequests(auth -> auth
            // 메인 화면(HTML)과 회원가입/로그인 API, 웹툰 검색 API는 로그인 없이 누구나 허용
            .requestMatchers("/", "/index.html", "/api/users/signup", "/api/users/login", "/api/webtoons/search").permitAll()
            // 그 외에 웹툰 등록, 삭제, 리뷰 작성 등 모든 요청은 인증(로그인)이 필요하도록 잠금
            .anyRequest().authenticated()
        );

        return http.build();
    }
}
