package com.pluralsight.quickstart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {

    @GetMapping(path = "/hello")
    public String helloWorld() {
        return "Hello World";
    }
}
