package com.example.read_file.dao;

import com.example.read_file.database.BookData;

import com.example.read_file.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAO {
    public List<Book> findAll() {
        return BookData.bookList;
    }

    public Book findById(int id) {
        for (Book book : BookData.bookList) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
