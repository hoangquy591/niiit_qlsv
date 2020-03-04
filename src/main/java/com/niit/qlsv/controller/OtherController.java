package com.niit.qlsv.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OtherController {

    @GetMapping("/admin")
    public String adminRestController() {
        return "Welcome to the admin house !";
    }

    @GetMapping("/user")
    public String userRestController() {
        return "Welcome user !";
    }
}
