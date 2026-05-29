package com.example.webtoon_archive.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebtoonSearchCondition {
    private String title; // 검색할 웹툰 제목
    private String platform; // 필터링할 플랫폼 (네이버, 카카오 등)
    private String status; // 필터링할 상태 (보는 중, 완결 등)
}
