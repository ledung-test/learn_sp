package com.example.read_file.database;


import com.example.read_file.utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitData implements CommandLineRunner {
    @Autowired
    private IFileReader iFileReader;
    @Override
    public void run(String... args) throws Exception {
//        BookData.bookList = iFileReader.readFile("books.json");
//        System.out.println(BookData.bookList);
        BookData.bookList = iFileReader.readFile("books.csv");
        BookData.bookList.forEach(System.out::println);
    }
}
