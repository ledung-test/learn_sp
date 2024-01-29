package com.example.movie.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertDirectorRequest {
    String nameDr;
    Date BirthdayDr;
    String descriptionDr;
}
