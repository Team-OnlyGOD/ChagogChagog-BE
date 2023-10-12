package com.onlygod.chagogchagogbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@ConfigurationPropertiesScan
@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class ChagogChagogBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChagogChagogBeApplication.class, args);
    }

}
