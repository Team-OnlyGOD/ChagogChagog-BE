package com.onlygod.chagogchagogbe.domain.chatbot.presentation;

import com.onlygod.chagogchagogbe.domain.chatbot.presentation.dto.request.ChatBotRequest;
import com.onlygod.chagogchagogbe.domain.chatbot.presentation.dto.response.ChatBotResponse;
import com.onlygod.chagogchagogbe.domain.chatbot.service.ChatBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatBotController {

    private final ChatBotService chatBotService;

    @PostMapping
    public ChatBotResponse chatBot(@RequestBody @Valid ChatBotRequest request) {
        return chatBotService.execute(request.getMessage());
    }
}
