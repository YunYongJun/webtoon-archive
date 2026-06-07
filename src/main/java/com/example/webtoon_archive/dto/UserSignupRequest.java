package com.example.webtoon_archive.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequest {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 4, max = 20, message = "아이디는 4자 이상 20자 이하로 입력해주세요.")
    private String username;
    
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 4, message = "비밀번호는 최소 4자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;
}
