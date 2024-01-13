package com.example.movie.entity;

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
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    Integer displayOrder;
    String videoUrl;
    Integer duration;
    Boolean status;
    Date createdAt;
    Date updatedAt;
    Date publishedAt;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
