package com.example.webtoon_archive.repository;

import com.example.webtoon_archive.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 로그인 시 아이디로 회원 정보를 조회하기 위한 메서드
    Optional<User> findByUsername(String username);
}
