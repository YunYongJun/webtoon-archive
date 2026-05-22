package com.example.webtoon_archive.repository;

import com.example.webtoon_archive.domain.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {
    
    // 나중에 구현할 기능
    List<Webtoon> findByPlatform(String platform);
}
