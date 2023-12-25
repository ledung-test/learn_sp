package com.example.stream_api.utils;

import com.example.stream_api.model.Person;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CSVFileReader implements IFileReader{
    public List<Person> readFile(String filePath) {
        List<Person> people = new ArrayList<>();

        try (InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(getClass()
                                                                     .getClassLoader()
                                                                     .getResourceAsStream(filePath)))) {

            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .build();

            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                Person person = new Person();
                person.setId(Integer.parseInt(row[0]));
                person.setName(row[1]);
                person.setJob(row[2]);
                person.setGender(row[3]);
                person.setCity(row[4]);
                person.setSalary(Integer.parseInt(row[5]));
                person.setBirthday(row[6]);

                people.add(person);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return people;
    }
}
