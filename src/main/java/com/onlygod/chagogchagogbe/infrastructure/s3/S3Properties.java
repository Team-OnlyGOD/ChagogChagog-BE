package com.onlygod.chagogchagogbe.infrastructure.s3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties("cloud.aws.s3")
public class S3Properties {

    private final String bucket;

}
