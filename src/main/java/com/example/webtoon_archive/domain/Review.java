package com.example.webtoon_archive.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String comment; // 한 줄 평

    private int rating; // 별점 (1 ~ 5점)

    // 어떤 웹툰의 리뷰인지 연결 (외래키/FK 역할)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id") // DB 상에서 webtoon_id 라는 컬럼으로 연결됨
    private Webtoon webtoon;

    // 생성자
    public Review(String comment, int rating, Webtoon webtoon) {
        this.comment = comment;
        this.rating = rating;
        this.webtoon = webtoon;
    }
}
