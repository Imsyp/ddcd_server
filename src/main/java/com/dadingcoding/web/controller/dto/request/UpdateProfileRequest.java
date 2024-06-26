package com.dadingcoding.web.controller.dto.request;

import com.dadingcoding.web.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateProfileRequest {
    private String username;
    private String email;
    private String phone;
}
