package com.example.sp_method_security.controller;

import com.example.sp_method_security.annotation.IsAdminOrSale;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brands")
public class BrandController {
    @GetMapping()
    @IsAdminOrSale
    public String getBrand(){
        return "Get brands";
    }
    @GetMapping("/create")
    @IsAdminOrSale
    public String createBrand(){
        return "Create brand";
    }
    @GetMapping("/update")
    @IsAdminOrSale
    public String updateBrand(){
        return "Update brand";
    }
    @GetMapping("/delete")
    @IsAdminOrSale
    public String deleteBrand(){
        return "Delete brand";
    }
}
