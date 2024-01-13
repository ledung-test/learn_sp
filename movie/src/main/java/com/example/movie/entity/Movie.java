package com.example.movie.entity;

import com.example.movie.model.enums.MovieType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;
    String slug;

    @Column(columnDefinition = "TEXT")
    String description;
    String poster;

    @Enumerated(EnumType.STRING)
    MovieType type;

    Integer releaseYear;
    Boolean status;
    Double rating;
    Integer view;

    Date createdAt;
    Date updatedAt;
    Date publishedAt;
}
