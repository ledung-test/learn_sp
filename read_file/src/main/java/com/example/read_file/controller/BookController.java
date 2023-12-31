package com.example.read_file.controller;

import com.example.read_file.model.Book;
import com.example.read_file.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id){
        return bookService.getBookById(id);
    }
}
