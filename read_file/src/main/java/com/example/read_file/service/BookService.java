package com.example.read_file.service;

import com.example.read_file.dao.BookDAO;
import com.example.read_file.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDAO bookDAO;

    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }
    public Book getBookById(int id) {
        return bookDAO.findById(id);
    }
}
