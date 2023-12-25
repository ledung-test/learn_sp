package com.example.stream_api.database;

import com.example.stream_api.dao.PersonDAO;
import com.example.stream_api.utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitData implements CommandLineRunner {
    @Autowired
    private IFileReader fileReader;
    @Override
    public void run(String... args) throws Exception {
        PersonDB.personList = fileReader.readFile("person.csv");
        //PersonDB.personList.forEach(System.out::println);
    }
}
