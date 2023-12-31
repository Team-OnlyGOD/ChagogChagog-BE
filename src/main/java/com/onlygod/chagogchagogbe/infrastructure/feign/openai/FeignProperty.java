package com.onlygod.chagogchagogbe.infrastructure.feign.openai;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;


@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "openfeign")
public class FeignProperty {

    private final String accessKey;
}
