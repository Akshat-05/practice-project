package com.practice.bucket4j.caffeine.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ProxyManager<String> proxyManager() {
        Caffeine<Object, Object> builder = Caffeine.newBuilder().maximumSize(100);
        return new CaffeineProxyManager<>(builder, Duration.ofMinutes(1));
    }


}
