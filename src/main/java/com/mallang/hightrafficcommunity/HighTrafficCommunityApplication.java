package com.mallang.hightrafficcommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HighTrafficCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(HighTrafficCommunityApplication.class, args);
    }

}