package com.onlygod.chagogchagogbe.domain.user.service;

import com.onlygod.chagogchagogbe.domain.user.domain.User;
import com.onlygod.chagogchagogbe.domain.user.domain.repository.UserRepository;
import com.onlygod.chagogchagogbe.domain.user.exception.InvalidPasswordException;
import com.onlygod.chagogchagogbe.domain.user.exception.UserNotFoundException;
import com.onlygod.chagogchagogbe.domain.user.presentation.dto.request.LoginRequest;
import com.onlygod.chagogchagogbe.domain.user.presentation.dto.response.TokenResponse;
import com.onlygod.chagogchagogbe.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenResponse execute(LoginRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        return new TokenResponse(
                jwtTokenProvider.generateAccessToken(user.getAccountId()),
                jwtTokenProvider.getExpiredAt()
        );
    }
}
