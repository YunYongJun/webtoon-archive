package com.example.webtoon_archive.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component // 스프링이 자동으로 관리하는 컴포넌트로 등록
public class JwtProvider {
    
    private final SecretKey key;
    // 토큰 유효 시간: 1시간 (60분 * 60초 * 1000밀리초)
    private final long tokenValidityInMillseconds = 3600000;

    public JwtProvider(@Value("${jwt.secret}") String secretString) {
        this.key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    }

    /* 1. 로그인 성공 시 인증 토큰(JWT) 발급 */
    public String createToken(String username, String nickname) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidityInMillseconds);

        return Jwts.builder()
        .subject(username) // 토큰 주인의 아이디 저장
        .claim("nickname", nickname) // 커스텀 데이터(닉네임) 저장
        .issuedAt(now) // 토큰 발행 시간
        .expiration(validity) // 토큰 만료 시간
        .signWith(key) // 비밀키로 암호화 서명
        .compact();
    }

    /* 2. 들어온 토큰이 유효한지 검증 */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false; // 토큰이 변조되었거나 만료되면 false 반환
        }
    }

    /* 3. 토큰에서 사용자 아이디(username) 꺼내기 */
    public String getUsername(String token) {
        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

}
