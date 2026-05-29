package com.example.webtoon_archive.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링 설정을 담은 클래스임을 선언
public class QuerydslConfig {

    @PersistenceContext
    private EntityManager em;

    @Bean // 프로젝트 어디서든 JPAQueryFactor를 꺼내 쓸 수 있도록 스프링 컨테이너에 등록
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}
