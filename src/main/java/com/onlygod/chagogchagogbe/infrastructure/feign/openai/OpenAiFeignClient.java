package com.onlygod.chagogchagogbe.infrastructure.feign.openai;

import com.onlygod.chagogchagogbe.infrastructure.feign.openai.dto.request.OpenAiRequest;
import com.onlygod.chagogchagogbe.infrastructure.feign.openai.dto.response.OpenAiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "openAI", url = "https://api.openai.com/v1/chat/completions")
public interface OpenAiFeignClient {

    @PostMapping
    OpenAiResponse getApi(@RequestBody OpenAiRequest request, @RequestHeader("Authorization") String token);
}
