package com.example.sp_method_security.controller;

import com.example.sp_method_security.annotation.IsAdminOrSaleOrAuthorOrUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-details")
public class UserDetailController {
    @GetMapping
    @IsAdminOrSaleOrAuthorOrUser
    public String getInformation(){
        return "Get information";
    }
}
