package com.example.sp_method_security.controller;

import com.example.sp_method_security.annotation.IsAdmin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping()
    @IsAdmin
    public String getUser(){
        return "Get Users";
    }
    @GetMapping("/create")
    @IsAdmin
    public String updateCreate(){
        return "Create User";
    }
    @GetMapping("/update")
    @IsAdmin
    public String updateUser(){
        return "Update User";
    }
}
