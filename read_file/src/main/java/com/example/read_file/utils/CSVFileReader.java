package com.example.read_file.utils;


import com.example.read_file.model.Book;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVFileReader implements IFileReader{

    @Override
    public List<Book> readFile(String filePath) throws IOException {
        try (InputStream csvData = getClass().getClassLoader().getResourceAsStream(filePath)) {
            try (CSVReader csvReader = new CSVReader(new InputStreamReader(csvData))) {
                List<String[]> rows = csvReader.readAll();
                return convertToBooks(rows);
            } catch (CsvException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private List<Book> convertToBooks(List<String[]> rows) {
        List<Book> books = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);
            Book book = new Book(
                    Integer.parseInt(row[0]),   // id
                    row[1],                     // title
                    row[2],                     // author
                    Integer.parseInt(row[3])    // year
            );
            books.add(book);
        }
        return books;
    }
}
