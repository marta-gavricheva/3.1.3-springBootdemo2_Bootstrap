package com.example.springBootdemo2.controller;

import com.example.springBootdemo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String indexPage() {
        return "Hello!";
    }

}
