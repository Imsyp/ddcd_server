package com.dadingcoding.web.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class MemberLoginResponseDto {
    private String token;
    private SimpleMemberResponseDto user;
}
