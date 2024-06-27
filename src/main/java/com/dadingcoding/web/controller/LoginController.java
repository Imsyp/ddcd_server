package com.dadingcoding.web.controller;

import com.dadingcoding.web.controller.dto.*;
import com.dadingcoding.web.domain.Notice;
import com.dadingcoding.web.response.ExceptResponse;
import com.dadingcoding.web.response.Response;
import com.dadingcoding.web.security.JwtToken;
import com.dadingcoding.web.service.MemberLoginService;
import com.dadingcoding.web.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class LoginController {

    private final MemberLoginService memberLoginService;
    private final NoticeService noticeService;

    // email 검증 -> valid -> 회원가입 정보 받아서 save -> login 해서 Token return
    @PostMapping("/signup-validate-email")
    public ValidateEmailResponseDto validateEmail(@RequestBody ValidateEmailRequestDto validateEmailRequestDto) {
        ValidateEmailResponseDto validateEmailResponseDto = memberLoginService.validateEmail(validateEmailRequestDto.getEmail());

        return validateEmailResponseDto;
    }

    @PostMapping("/signup")
    public ResponseEntity<Response> signIn(@RequestBody MemberSignInDto memberSignInDto) {
        memberLoginService.save(memberSignInDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response(200, "회원가입에 성공하였습니다."));
        // TODO : Response 추가 필요
    }

    @PostMapping("/login")
    public JwtToken login(@RequestBody MemberLoginDto memberLoginDto) {
        return memberLoginService.makeToken(memberLoginDto.getEmail(), memberLoginDto.getPassword());
    }

    @PostMapping("/refresh")
    public AccessTokenDto refreshToken(@RequestBody MemberSignInDto memberSignInDto, @CookieValue("refreshToken") String refreshToken) {
        // Cookie -> RefreshToken 받아서
        // DB상의 Refresh 토큰과 동일 && 만료되지 않았는지 확인 -> 재발급
        String accessToken = memberLoginService.refresh(memberSignInDto.getEmail(), refreshToken);

        return new AccessTokenDto(memberSignInDto.getEmail(), accessToken);
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("/notice/{id}")
    public ResponseEntity<?> findNotice(@PathVariable long id) {
        try {
            Notice notice = noticeService.findById(id);

            // Notice 객체를 JSON 형태로 변환하여 반환
            Map<String, Object> noticeResponse = new HashMap<>();
            noticeResponse.put("id", notice.getId());
            noticeResponse.put("title", notice.getTitle());
            noticeResponse.put("content", notice.getContent());
            noticeResponse.put("datePosted", notice.getDatePosted().toString());
            noticeResponse.put("visibility", notice.getVisibility());

            // 성공적인 응답 반환
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response(200, noticeResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptResponse(500, "서버 내부 오류", false));
        }
    }
}
