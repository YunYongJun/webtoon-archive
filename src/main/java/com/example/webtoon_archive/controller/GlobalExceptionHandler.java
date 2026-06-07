package com.example.webtoon_archive.controller;

import com.example.webtoon_archive.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice // 모든 RestController에서 발생하는 예외를 여기서 모아서 처리
public class GlobalExceptionHandler {

    /* 
    * 우리가 서비스단에서 던졌던 IllegalArgumentException을 처리 
    * 잘못된 ID를 조회하거나 인자 값이 잘못되었을 때 400(Bad Request)을 반환
    */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.name(),
            ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /*
    * 그 외에 예상치 못한 서버 내부의 모든 에러(최상위 Exception)를 처리함
    * 500(Internal Server Error)을 반환
    */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.name(),
            "서버 내부에 오류가 발생했습니다. 관리자에게 문의하세요."
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * DTO 유효성 검증(@Valid) 실패 시 발생하는 예외를 처리합니다. (400 Bad Request)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {
        
        // 에러 메시지 중 첫 번째 에러 문구를 가져옵니다.
        String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        com.example.webtoon_archive.dto.ErrorResponse errorResponse = new com.example.webtoon_archive.dto.ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                defaultMessage
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
