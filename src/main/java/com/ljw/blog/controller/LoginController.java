package com.ljw.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping(value = {"/login"})
    public String login() {
        return "login";
    }

    @PostMapping("/all")
    public String toAllBlogs() {
        return "AllBlogs";
    }

    @GetMapping("/regeist")
    public String toRegeist() {
        return "regist";
    }

    @GetMapping("/")
    public String allPermits() {
        return "index";
    }
}
