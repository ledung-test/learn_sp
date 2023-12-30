package com.example.mini_pr.controller;

import com.example.mini_pr.model.Product;
import com.example.mini_pr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getAllProducts(Model model, @RequestParam(required = false) String search){
        List<Product> productList;
        if (search != null){
            productList = productService.search(search);
        }else {
            productList = productService.getAllProducts();
        }
        model.addAttribute("productList", productList);
        return "getAllProducts";
    }
    @GetMapping("/products/{id}")
    public String getProductById(Model model, @PathVariable int id){
        model.addAttribute("product", productService.getProductById(id));
        return "getProductById";
    }

}
