package com.sofrecom.sn3.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/test")
@RestController
@CrossOrigin(origins = "*")

public class TestController {

    @GetMapping
    public String test() {
        return "test";
    }
}
