package com.example.mini_pr.dao;

import com.example.mini_pr.database.ProductData;
import com.example.mini_pr.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {
    public List<Product> findAll() {
        return ProductData.productList;
    }

    public Product findById(int id) {
        for (Product product : ProductData.productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    public List<Product> search(String keyword) {
        return ProductData.productList.stream()
                .filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
}
