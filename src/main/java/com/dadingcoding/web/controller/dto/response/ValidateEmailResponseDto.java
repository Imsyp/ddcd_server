package com.dadingcoding.web.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class ValidateEmailResponseDto {
    private boolean isValid;
    private boolean isUnique;
}
