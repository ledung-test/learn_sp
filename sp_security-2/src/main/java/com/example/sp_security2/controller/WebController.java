package com.example.sp_security2.controller;

import com.example.sp_security2.model.anotion.IsAdmin;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {
    //Ai cũng có thể truy cập

    @GetMapping("/")
    public String getHome(){
        return "index";
    }
    //Có Role User mới truy cập được
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/profile")
    public String getProfile(){
        return "profile";
    }
    //Có Role Admin mới truy cập được

    //@PreAuthorize("hasRole('ADMIN')")
    @IsAdmin
    @GetMapping("/admin")
    public String getAdmmin(){
        return "admin";
    }

    //Trang login
    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
}
