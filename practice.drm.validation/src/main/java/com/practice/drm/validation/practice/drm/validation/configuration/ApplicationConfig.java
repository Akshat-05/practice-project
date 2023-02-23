package com.practice.drm.validation.practice.drm.validation.configuration;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public OkHttpClient getWeb() {
        return new OkHttpClient();
    }

}
