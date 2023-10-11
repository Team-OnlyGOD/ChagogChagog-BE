package com.onlygod.chagogchagogbe.domain.user.presentation;

import com.onlygod.chagogchagogbe.domain.user.presentation.dto.request.LoginRequest;
import com.onlygod.chagogchagogbe.domain.user.presentation.dto.response.TokenResponse;
import com.onlygod.chagogchagogbe.domain.user.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final LoginService loginService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.execute(request);
    }
}
