package com.example.sp_method_security.controller;

import com.example.sp_method_security.annotation.IsAdminOrSale;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @GetMapping()
    @IsAdminOrSale
    public String getCategory(){
        return "Get categories";
    }
    @GetMapping("/create")
    @IsAdminOrSale
    public String createCategory(){
        return "Create category";
    }
    @GetMapping("/update")
    @IsAdminOrSale
    public String updateCategory(){
        return "Update category";
    }
    @GetMapping("/delete")
    @IsAdminOrSale
    public String deleteCategory(){
        return "Delete category";
    }
}
