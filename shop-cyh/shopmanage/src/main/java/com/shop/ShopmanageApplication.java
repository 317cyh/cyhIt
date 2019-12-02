package com.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class ShopmanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopmanageApplication.class, args);
    }

}
