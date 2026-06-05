package com.example.webtoon_archive.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
        throws ServletException, IOException {
        
        // 1. 브라우저가 보낸 HTTP 요청 헤더에서 JWT 토큰을 꺼냄
        String token = resolveToken(request);
        
        // 2. 토큰이 유효하다면 스프링 시큐리티에게 "인증된 사용자"라고 말해줌
        if (token != null && jwtProvider.validateToken(token)) {
            String username = jwtProvider.getUsername(token);

            UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            
            // 시큐리티 컨텍스트에 인증 정보를 담아둠 (이게 있어야 시큐리티 방어벽이 통과됨)
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 3. 다음 필터(다음 단계)로 요청을 넘김
        filterChain.doFilter(request, response);
    }

    // HTTP 헤더에서 "Authorization: Bearer 토큰값"을 뜯어내는 메서드
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 뒷 부분의 순수 토큰만 자름
        }
        return null;
    }
}
