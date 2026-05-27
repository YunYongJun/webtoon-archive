package com.example.webtoon_archive.dto;

import com.example.webtoon_archive.domain.Webtoon;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class WebtoonSaveRequest {
    private String title;
    private String author;
    private String platform;
    private String status;

    // 받아온 DTO 바구니의 데이터를 진짜 DB에 저장할 엔티티 객체로 변환해줌
    public Webtoon toEntity() {
        return new Webtoon(title, author, platform, status);
    }
}
