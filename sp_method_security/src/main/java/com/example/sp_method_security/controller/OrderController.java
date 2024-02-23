package com.example.sp_method_security.controller;

import com.example.sp_method_security.annotation.IsAdminOrSale;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @GetMapping()
    @IsAdminOrSale
    public String getOrder(){
        return "Get orders";
    }
    @GetMapping("/create")
    @IsAdminOrSale
    public String createOrder(){
        return "Create order";
    }
    @GetMapping("/update")
    @IsAdminOrSale
    public String updateOrder(){
        return "Update order";
    }

}
