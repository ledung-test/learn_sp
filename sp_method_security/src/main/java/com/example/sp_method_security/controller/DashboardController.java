package com.example.sp_method_security.controller;

import com.example.sp_method_security.annotation.IsAdminOrSale;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboards")
public class DashboardController {
    @GetMapping()
    @IsAdminOrSale
    public String getDashboard(){
        return "Get Dashboard";
    }
}
