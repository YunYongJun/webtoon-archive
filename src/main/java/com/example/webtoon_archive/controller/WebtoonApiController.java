package com.example.webtoon_archive.controller;

import com.example.webtoon_archive.domain.Webtoon;
import com.example.webtoon_archive.service.WebtoonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 이 클래스가 JSON 데이터를 반환하는 REST API 컨트롤러임을 선언
@RequestMapping("/api/webtoons") // 이 컨트롤러의 모든 주소는 /api/webtoons 로 시작
public class WebtoonApiController {
    
    private final WebtoonService webtoonService;

    // 생성자 주입으로 서비스 계층을 가져옴
    public WebtoonApiController(WebtoonService webtoonService) {
        this.webtoonService = webtoonService;
    }

    /* 1. 웹툰 등록 API (POST 방식) 
    * 브라우저에서 보낸 JSON 데이터를 받아와 DB에 저장
    */
   @PostMapping
   public Long createWebtoon(@RequestBody Webtoon webtoon) {
        // RequestBody는 사용자가 보낸 JSON 데이터를 자바 객체(Webtoon)로 자동 변환해 줌
        return webtoonService.saveWebtoon(webtoon);
   }

   /* 2. 웹툰 전체 목록 조회 API (GET 방식) */
   @GetMapping
   public List<Webtoon> getAllWebtoons() {
        return webtoonService.findAllWebtoons();
   }

}
