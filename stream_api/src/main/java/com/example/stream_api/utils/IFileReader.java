package com.example.stream_api.utils;

import com.example.stream_api.model.Person;

import java.io.IOException;
import java.util.List;

public interface IFileReader {
    List<Person> readFile(String filePath) throws IOException;
}
