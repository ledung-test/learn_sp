package com.example.stream_api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {
    int id;
    String name;
    String job;
    String gender;
    String city;
    int salary;
    LocalDate birthday;
    private static final DateTimeFormatter dateFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public void setBirthday(String birthday) {
        this.birthday = LocalDate.parse(birthday, dateFormatter);

    }
}
