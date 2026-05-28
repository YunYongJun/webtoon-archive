package com.example.webtoon_archive.dto;

import com.example.webtoon_archive.domain.Webtoon;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebtoonResponse {
    private Long id;
    private String title;
    private String author;
    private String platform;
    private String status;

    // 엔티티를 받아서 DTO 객체로 변환해주는 생성자
    public WebtoonResponse(Webtoon webtoon) {
        this.id = webtoon.getId();
        this.title = webtoon.getTitle();
        this.author = webtoon.getAuthor();
        this.platform = webtoon.getPlatform();
        this.status = webtoon.getStatus();
    }
}
