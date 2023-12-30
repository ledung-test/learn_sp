package com.example.mini_pr.service;

import com.example.mini_pr.dao.ProductDAO;
import com.example.mini_pr.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDAO productDAO;
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }
    public Product getProductById(int id) {
        return productDAO.findById(id);
    }
    public List<Product> search(String keyword){
        return productDAO.search(keyword);
    }
}
