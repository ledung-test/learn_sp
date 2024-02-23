package com.example.sp_method_security.controller;

import com.example.sp_method_security.annotation.IsAdminOrSale;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping()
    @IsAdminOrSale
    public String getProduct(){
        return "Get products";
    }
    @GetMapping("/create")
    @IsAdminOrSale
    public String createProduct(){
        return "Create product";
    }
    @GetMapping("/update")
    @IsAdminOrSale
    public String updateProduct(){
        return "Update product";
    }
    @GetMapping("/delete")
    @IsAdminOrSale
    public String deleteProduct(){
        return "Delete product";
    }
}
