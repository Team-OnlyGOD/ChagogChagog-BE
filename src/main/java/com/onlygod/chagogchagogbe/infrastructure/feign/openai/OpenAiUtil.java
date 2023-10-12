package com.onlygod.chagogchagogbe.infrastructure.feign.openai;

import com.onlygod.chagogchagogbe.infrastructure.feign.openai.dto.request.OpenAiRequest;
import com.onlygod.chagogchagogbe.infrastructure.feign.openai.dto.response.OpenAiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OpenAiUtil {

    private final OpenAiFeignClient openAiFeignClient;
    private final FeignProperty feignProperty;

    public String getGPTAnswer(String request) {
        OpenAiResponse response = openAiFeignClient.getApi(
                OpenAiRequest.builder()
                        .model("gpt-3.5-turbo-16k")
                        .messages(
                                List.of(
                                        new OpenAiRequest.Message(
                                        "system",
                                        "재고관리에 관한 질문을 받아 대답하는 재고관리 전문가, 대답을 회피하지않고 물어본것을 똑바로 대답"
                                        ),
                                        new OpenAiRequest.Message(
                                                "user",
                                                request
                                        ),
                                        new OpenAiRequest.Message(
                                                "assistant",
                                                "정확한 기간를 반환"
                                        )
                                )
                        )
                        .temperature(1)
                        .max_tokens(3897)
                        .top_p(1)
                        .frequency_penalty(0)
                        .presence_penalty(0)
                        .build(),
                "Bearer " + feignProperty.getAccessKey()
        );

        return response.getChoices().get(0).getMessage().getContent();
    }
}
