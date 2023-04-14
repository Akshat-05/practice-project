package com.sping.solr.banchmarking.configuration;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {

    @Bean
    public OkHttpClient getWeb() {
        return new OkHttpClient();
    }

    @Bean
    public WebClient getWeb1() {
        return WebClient.create();
    }

}
