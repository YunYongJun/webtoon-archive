package com.example.webtoon_archive.controller;

import com.example.webtoon_archive.dto.UserSignupRequest;
import com.example.webtoon_archive.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserApiController {
    
    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    /* 회원가입 API 
    * 주소: POST /api/users/signup
    */
    @PostMapping("/signup")
    public String signup(@RequestBody UserSignupRequest request) {
        userService.signup(request);
        return "회원가입이 성공적으로 완료되었습니다.";
    }
}
