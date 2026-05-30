package com.example.webtoon_archive.service;

import com.example.webtoon_archive.domain.Webtoon;
import com.example.webtoon_archive.repository.WebtoonRepository;
import com.example.webtoon_archive.dto.WebtoonSearchCondition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 이 클래스가 스프링의 비즈니스 로직을 담당하는 Service임을 선언
@Transactional(readOnly = true) // 기본적으로 데이터를 읽기만 하는 메서드들은 최적화를 위해 읽기 전용으로 설정
public class WebtoonService {
    
    // 레포지토리를 가져와서 사용해야 하므로 주입을 받음
    private final WebtoonRepository webtoonRepository;

    // 생성자 주입 방식 (스프링이 자동으로 의존성을 연결해 줌)
    public WebtoonService(WebtoonRepository webtoonRepository) {
        this.webtoonRepository = webtoonRepository;
    }

    /* 1. 새 웹툰 저장하기 */ 
    @Transactional // 데이터를 DB에 넣거나 수정할 때는 readOnly를 끄기 위해 별도로 붙임
    public Long saveWebtoon(Webtoon webtoon) {
        Webtoon saveWebtoon = webtoonRepository.save(webtoon);
        return saveWebtoon.getId(); // 저장된 웬툰의 고유 ID(PK)를 반환
    }

    /* 2. 전체 웹툰 목록 조회하기 */
    public List<Webtoon> findAllWebtoons() {
        return webtoonRepository.findAll();
    }

    /* 3. 특정 웹툰 1개 조회하기 */
    public Webtoon findOne(Long id) {
        // 만약 찾는 ID가 없으면 에러를 발생시키거나 비어있는 객체를 처리하도록 예외처리
        return webtoonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 웹툰입니다. id = " + id));
    }

    /* 4. 특정 웹툰 삭제하기 */
    @Transactional
    public void deleteWebtoon(Long id) {
        webtoonRepository.deleteById(id);
    }

    /* 5. 조건별 웹툰 동적 검색하기 (QueryDSL 반영) */
    public List<Webtoon> searchWebtoons(WebtoonSearchCondition condition) {
        return webtoonRepository.search(condition);
    }
}
