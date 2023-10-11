package com.onlygod.chagogchagogbe.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TokenResponse {
    private final String accessToken;
    private final LocalDateTime expiredAt;
}
