package com.example.mini_pr.utils;

import com.example.mini_pr.model.Product;

import java.io.IOException;
import java.util.List;

public interface IFileReader {
    List<Product> readFile(String filePath) throws IOException;
}
