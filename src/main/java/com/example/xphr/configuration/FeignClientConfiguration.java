package com.example.xphr.configuration;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign Client Configuration.
 */
@Configuration
public class FeignClientConfiguration {
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
