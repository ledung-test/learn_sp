package com.example.read_file.utils;

import com.example.read_file.model.Book;

import java.io.IOException;
import java.util.List;

public interface IFileReader{
    List<Book> readFile(String filePath) throws IOException;

}
