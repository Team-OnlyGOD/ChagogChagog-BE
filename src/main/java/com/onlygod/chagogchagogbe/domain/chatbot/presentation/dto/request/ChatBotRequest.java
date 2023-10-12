package com.onlygod.chagogchagogbe.domain.chatbot.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ChatBotRequest {

    @NotBlank
    private String message;
}
