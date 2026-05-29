package com.example.webtoon_archive.repository;

import com.example.webtoon_archive.domain.QWebtoon;
import com.example.webtoon_archive.domain.Webtoon;
import com.example.webtoon_archive.dto.WebtoonSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import java.util.List;

public class WebtoonRepositoryCustomImpl implements WebtoonRepositoryCustom {
    
    private final JPAQueryFactory queryFactory;

    public WebtoonRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Webtoon> search(WebtoonSearchCondition condition) {
        QWebtoon webtoon = QWebtoon.webtoon; // 빌드할 때 만든 Q클래스

        return queryFactory.selectFrom(webtoon)
        .where(
            titleContains(condition.getTitle()), // 제목 포함 조건
            platformEq(condition.getPlatform()), // 플랫폼 일치 조건
            statusEq(condition.getStatus()) // 상태 일치 조건
        ).fetch();
    }

    // 조건 값이 비어있지 않을 때만 데이터베이스에 SQL 조건문(WHERE)을 동적으로 붙여주는 헬퍼 메서드들
    private BooleanExpression titleContains(String title) {
        return StringUtils.hasText(title) ? QWebtoon.webtoon.title.contains(title) : null;
    }

    private BooleanExpression platformEq(String platform) {
        return StringUtils.hasText(platform) ? QWebtoon.webtoon.platform.eq(platform) : null;
    }

    private BooleanExpression statusEq(String status) {
        return StringUtils.hasText(status) ? QWebtoon.webtoon.status.eq(status) : null;
    }
}
