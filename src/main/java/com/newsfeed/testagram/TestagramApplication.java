package com.newsfeed.testagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TestagramApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestagramApplication.class, args);
    }

}
