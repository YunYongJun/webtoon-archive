package com.example.webtoon_archive.service;

import com.example.webtoon_archive.domain.Webtoon;
import com.example.webtoon_archive.domain.Review;
import com.example.webtoon_archive.repository.ReviewRepository;
import com.example.webtoon_archive.repository.WebtoonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final WebtoonRepository webtoonRepository;

    public ReviewService(ReviewRepository reviewRepository, WebtoonRepository webtoonRepository) {
        this.reviewRepository = reviewRepository;
        this.webtoonRepository = webtoonRepository;
    }

    /* 특정 웹툰에 리뷰 등록하기 */

    @Transactional
    public Long saveReview(Long webtoonId, String comment, int rating) {
        // 1. 해당 웹툰이 진짜 존재하는지 조회
        Webtoon webtoon = webtoonRepository.findById(webtoonId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 웹툰입니다. id = " + webtoonId));

        // 2. 리뷰 객체 생성 및 웹툰 연결
        Review review = new Review(comment, rating, webtoon);

        // 3. 저장
        Review saveReview = reviewRepository.save(review);
        return saveReview.getId();
    }

    /* 특정 웹툰의 모든 리뷰 조회하기 */
    public List<Review> findReviewsByWebtoon(Long webtoonId) {
        return reviewRepository.findByWebtoonId(webtoonId);
    }
}
