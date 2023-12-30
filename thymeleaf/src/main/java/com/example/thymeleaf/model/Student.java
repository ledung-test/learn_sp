package com.example.thymeleaf.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    int id;
    String name;
    String email;
    String phone;
    double score;
}
