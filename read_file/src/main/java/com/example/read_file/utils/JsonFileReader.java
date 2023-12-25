//package com.example.test.utils;
//
//import com.example.test.model.Book;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//@Component
//public class JsonFileReader implements IFileReader{
//
//    @Override
//    public List<Book> readFile(String filePath) throws IOException {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            // Read the JSON file
//            InputStream jsonData = getClass().getClassLoader().getResourceAsStream(filePath);
//
//            // Convert JSON to List of Post objects
//            return objectMapper.readValue(jsonData, new TypeReference<List<Book>>() {});
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }
//
//}
