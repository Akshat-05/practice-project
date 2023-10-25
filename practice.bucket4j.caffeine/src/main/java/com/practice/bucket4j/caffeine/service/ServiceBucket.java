package com.practice.bucket4j.caffeine.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

import java.time.Duration;

public class ServiceBucket {

    public Bucket createBucket(){
        Bandwidth limit = Bandwidth.classic(1, Refill.intervally(1, Duration.ofMinutes(1)));
        Bucket bucket = Bucket.builder()
                .addLimit(limit)
                .build();
        return bucket;
    }
}
