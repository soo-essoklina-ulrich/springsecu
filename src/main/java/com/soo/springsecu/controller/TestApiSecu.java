package com.soo.springsecu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestApiSecu {
    @GetMapping("/api/test")
    public String testApi() {
        return "Hello World";
    }
}
