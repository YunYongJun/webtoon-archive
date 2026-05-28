package com.example.webtoon_archive.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ErrorResponse {
    private int status; // HTTP 상태 코드 (예: 400, 404, 500)
    private String error; // 에러 이름 (예: BAD_REQUEST)
    private String message; // 에러 메세지 (예: 존재하지 않는 웹툰입니다.)

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
