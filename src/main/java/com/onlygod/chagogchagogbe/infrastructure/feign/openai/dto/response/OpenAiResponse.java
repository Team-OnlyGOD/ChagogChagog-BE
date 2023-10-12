package com.onlygod.chagogchagogbe.infrastructure.feign.openai.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@JsonNaming(value = PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@NoArgsConstructor
public class OpenAiResponse {

    private String id;
    private String object;
    private Integer created;
    private String model;
    private List<Choice> choices;
    private Usage usage;


    @Getter
    @NoArgsConstructor
    public static class Choice{
        private Integer index;
        private Message message;
        private String finishReason;
    }

    @Getter
    @NoArgsConstructor
    public static class Message{
        private String role;
        private String content;
    }

    @Getter
    @NoArgsConstructor
    public static class Usage{
        private Integer promptTokens;
        private Integer completionTokens;
        private Integer totalTokens;
    }
}

