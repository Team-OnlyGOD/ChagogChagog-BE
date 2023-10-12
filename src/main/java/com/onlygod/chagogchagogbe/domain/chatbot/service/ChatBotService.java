package com.onlygod.chagogchagogbe.domain.chatbot.service;

import com.onlygod.chagogchagogbe.domain.chatbot.presentation.dto.response.ChatBotResponse;
import com.onlygod.chagogchagogbe.infrastructure.feign.openai.OpenAiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatBotService {

    private final OpenAiUtil openAiUtil;

    @Transactional
    public ChatBotResponse execute(String request) {
        return new ChatBotResponse(
                openAiUtil.getGPTAnswer(request),
                true
        );
    }
}
