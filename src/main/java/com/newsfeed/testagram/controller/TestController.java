package com.newsfeed.testagram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/test-exception")
    public String throwException() {
        throw new RuntimeException("테스트용 예외 발생");
    }

}
