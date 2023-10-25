package com.practice.bucket4j.caffeine.controller;

import com.practice.bucket4j.caffeine.config.RateLimiter;
import io.github.bucket4j.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RateLimiter rateLimiter;

    @GetMapping("/test/{key}")
    public ResponseEntity<String> testEndPoint(@PathVariable String key) {
        Bucket bucket = rateLimiter.resolveBucket(key);
        for(int i = 0; i < 10; i++){
            if(bucket.tryConsume(1)){
                System.out.println("within limit");
            } else {
                System.out.println("limit exceeded");
            }
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("Middleware Before Tv Scheduler Service");
    }

}
