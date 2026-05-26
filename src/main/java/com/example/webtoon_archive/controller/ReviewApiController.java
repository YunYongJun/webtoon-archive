package com.example.webtoon_archive.controller;

import com.example.webtoon_archive.domain.Review;
import com.example.webtoon_archive.service.ReviewService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewApiController {
    private final ReviewService reviewService;

    public ReviewApiController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /* 특정 웹툰에 리뷰 등록 API 
    * 주소 예시: POST /api/webtoons/1/reviews
    */
    @PostMapping("/webtoons/{webtoonId}/reviews")
    public Long createReview(@PathVariable("webtoonId") Long webtoonId, @RequestBody ReviewRequest request) {
        return reviewService.saveReview(webtoonId, request.getComment(), request.getRating());
    }

    /* 2. 특정 웹툰의 리뷰 목록 조회 API 
    * 주소 예시: GET /api/webtoons/1/reviews
    */
    @GetMapping("/webtoons/{webtoonId}/reviews")
    public List<Review> getReviews(@PathVariable("webtoonId") Long webtoonId) {
        return reviewService.findReviewsByWebtoon(webtoonId);
    }


    /* DTO(Data Transfer Object) 
    * 화면에서 넘어오는 한 줄 평과 별점 데이터를 안전하게 받아내기 위한 바구니 클래스
    */
    @Getter
    @Setter
    public static class ReviewRequest {
        private String comment;
        private int rating;
    }
}
