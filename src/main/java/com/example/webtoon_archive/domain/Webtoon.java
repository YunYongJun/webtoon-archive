package com.example.webtoon_archive.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 이 클래스가 데이터베이스 테이블과 매핑됨을 알림
@Table(name = "webtoons") // 생성될 DB 테이블 이름을 지정
@Getter // 모든 필드의 getter 메서드를 자동으로 생성
@Setter // 모든 필드의 setter 메서드를 자동으로 생성
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 만들어 줌
public class Webtoon {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL의 auto_increment(번호 자동 증가)를 사용
    private Long id;

    @Column(nullable = false, length = 100) // 비어있을 수 없고, 최대 100자 제한
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(length = 30) // 플랫폼 (예 : 네이버웹툰, 카카오페이지 등)
    private String platform;

    @Column(length = 20) // 상태 (예 : 보는중, 완결, 정주행완료, 하차)
    private String status;
    
    @ManyToOne(fetch = FetchType.LAZY) // 하나의 회원은 여러 갸의 웹툰을 아카이빙할 수 있음
    @JoinColumn(name = "user_id") // 외래키(FK) 컬럼명 지정
    private User user;

    public Webtoon(String title, String author, String platform, String status) {
        this.title = title;
        this.author = author;
        this.platform = platform;
        this.status = status;
    }
}
