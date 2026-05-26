package com.example.webtoon_archive.repository;

import com.example.webtoon_archive.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 특정 웹툰의 리뷰들만 모아서 가져오는 특별한 메서드
    List<Review> findByWebtoonId(Long webtoonId);
}
