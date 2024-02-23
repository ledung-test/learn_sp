package com.example.sp_method_security.controller;

import com.example.sp_method_security.annotation.IsAdminOrSale;
import com.example.sp_method_security.annotation.IsAdminOrSaleOrAuthor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @GetMapping()
    @IsAdminOrSaleOrAuthor
    public String getBlog(){
        return "Get blogs";
    }
    @GetMapping("/create")
    @IsAdminOrSaleOrAuthor
    public String createBlog(){
        return "Create blog";
    }
    @GetMapping("/update")
    @IsAdminOrSaleOrAuthor
    public String updateBlog(){
        return "Update blog";
    }
    @GetMapping("/delete")
    @IsAdminOrSaleOrAuthor
    public String deleteBlog(){
        return "Delete blog";
    }
}
