package com.example.webtoon_archive.repository;

import com.example.webtoon_archive.domain.Webtoon;
import com.example.webtoon_archive.dto.WebtoonSearchCondition;
import java.util.List;

public interface WebtoonRepositoryCustom {
    // 우리가 QueryDSL로 직접 본문을 구현할 동적 검색 메서드 정의
    List<Webtoon> search(WebtoonSearchCondition condition);
}
