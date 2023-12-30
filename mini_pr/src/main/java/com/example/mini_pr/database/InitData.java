package com.example.mini_pr.database;

import com.example.mini_pr.utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitData implements CommandLineRunner {
    @Autowired
    private IFileReader fileReader;
    @Override
    public void run(String... args) throws Exception {
        ProductData.productList = fileReader.readFile("product.json");
        ProductData.productList.forEach(System.out::println);
    }
}
