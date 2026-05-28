package com.example.webtoon_archive.controller;

import com.example.webtoon_archive.dto.WebtoonSaveRequest;
import com.example.webtoon_archive.dto.WebtoonResponse;
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

    /* 1. 웹툰 등록 API (POST 방식 / DTO 반영) 
    * 브라우저에서 보낸 JSON 데이터를 받아와 DB에 저장
    */
   @PostMapping
   public Long createWebtoon(@RequestBody WebtoonSaveRequest request) {
        // RequestBody는 사용자가 보낸 JSON 데이터를 자바 객체로 자동 변환해 줌
        // 1. 화면에서 보낸 JSON이 WebtoonSaveRequest DTO 객체로 안전하게 바인딩됨
        // 2. DTO를 엔티티로 변환하여 서비스단으로 넘겨줌
        Webtoon webtoon = request.toEntity();
        return webtoonService.saveWebtoon(webtoon);
   }

   /* 2. 웹툰 전체 목록 조회 API (GET 방식 / DTO 반영) */
   @GetMapping
   public List<WebtoonResponse> getAllWebtoons() {
          List<Webtoon> webtoons = webtoonService.findAllWebtoons();
        return webtoons.stream().map(WebtoonResponse::new).toList();
   }

   /* 3. 웹툰 삭제 API (DELETE 방식) 
   * 주소창에 /api/webtoons/{id} 형태로 들어오는 ID 값을 받아 해당 웹툰을 지움
   */
   @DeleteMapping("/{id}")
   public void deleteWebtoon(@PathVariable("id") Long id) {
     // @PathVariable은 주소창의 {id} 자리에 들어오는 숫자를 자바 변수로 매핑해 줌
          webtoonService.deleteWebtoon(id);
   }
   
}
