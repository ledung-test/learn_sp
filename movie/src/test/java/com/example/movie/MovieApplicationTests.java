package com.example.movie;

import com.example.movie.entity.Movie;
import com.example.movie.model.enums.MovieType;
import com.example.movie.reponsitory.MovieRepository;
import com.github.slugify.Slugify;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MovieApplicationTests {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void test() {
        Faker faker = new Faker(); // Faker data
        Slugify slugify = Slugify.builder().build(); // Generate slug

        for (int i = 0; i < 100; i++) {
            String title = faker.book().title();
            boolean status = faker.bool().bool();
            Date createdAt = faker.date().birthday();
            Date publishedAt = null;

            if (status) {
                publishedAt = createdAt;
            }

            Movie movie = Movie.builder()
                    .title(title)
                    .slug(slugify.slugify(title))
                    .description(faker.lorem().paragraph())
                    .poster(generateLinkImage(title))
                    .type(MovieType.values()[faker.number().numberBetween(0, 3)])
                    .releaseYear(faker.number().numberBetween(2018, 2023))
                    .status(status)
                    .rating(faker.number().randomDouble(1, 1, 5))
                    .view(faker.number().numberBetween(100, 1000))
                    .createdAt(createdAt)
                    .updatedAt(createdAt)
                    .publishedAt(publishedAt)
                    .build();
            movieRepository.save(movie);
        }
    }
    private static String getCharacter(String str) {
        return str.substring(0, 1).toUpperCase();
    }
    public static String generateLinkImage(String name) {
        return "https://placehold.co/218x290?text=" + getCharacter(name);
    }

}
