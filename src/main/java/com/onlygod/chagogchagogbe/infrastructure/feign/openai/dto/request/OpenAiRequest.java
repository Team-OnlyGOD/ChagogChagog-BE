package com.onlygod.chagogchagogbe.infrastructure.feign.openai.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OpenAiRequest {
    private String model;
    private List<Message> messages;
    private Integer temperature;
    private Integer max_tokens;
    private Integer top_p;
    private Integer frequency_penalty;
    private Integer presence_penalty;

    @Getter
    @AllArgsConstructor
    public static class Message{
        private String role;
        private String content;
    }
}
