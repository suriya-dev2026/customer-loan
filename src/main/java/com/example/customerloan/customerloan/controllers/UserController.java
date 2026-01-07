package com.example.customerloan.customerloan.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.customerloan.customerloan.request.UserRequest;

@RestController
public class UserController {

    

    @PostMapping("/register")
    public String register(@RequestBody UserRequest userRequest){
        return "user register successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequest userRequest){
        return "user login successfully";
    }

}
