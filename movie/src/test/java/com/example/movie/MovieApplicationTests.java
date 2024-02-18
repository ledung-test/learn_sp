package com.example.movie;

import com.example.movie.entity.*;
import com.example.movie.model.enums.MovieType;
import com.example.movie.model.enums.UserRole;
import com.example.movie.reponsitory.*;
import com.github.slugify.Slugify;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
class MovieApplicationTests {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void test_actor(){
        Faker faker = new Faker();
        for (int i = 0; i < 50; i++) {
            String name = faker.name().fullName();
            Actor actor = Actor.builder()
                    .name(name)
                    .birthday(faker.date().birthday())
                    .description(faker.lorem().paragraph())
                    .avatar(generateLinkImage(name))
                    .build();
            actorRepository.save(actor);
        }

    }
    @Test
    void test_director(){
        Faker faker = new Faker();
        for (int i = 0; i < 50; i++) {
            String name = faker.name().fullName();
            Director director = Director.builder()
                    .name(name)
                    .birthday(faker.date().birthday())
                    .description(faker.lorem().paragraph())
                    .avatar(generateLinkImage(name))
                    .build();
            directorRepository.save(director);
        }
    }
    @Test
    void test_genre(){
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Genre genre = Genre.builder()
                    .name(faker.book().genre())
                    .build();
            genreRepository.save(genre);
        }
    }
    @Test
    void test_user(){
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            String name= faker.name().fullName();
            User user = User.builder()
                    .name(name)
                    .email(faker.internet().emailAddress())
                    .password(bCryptPasswordEncoder.encode("123"))
                    .avatar(generateLinkImage(name))
                    .role(i == 0 || i == 1 ? UserRole.ADMIN : UserRole.USER)
                    .build();
            userRepository.save(user);
        }

    }
    @Test
    void test_blog(){
        Faker faker = new Faker();
        Random random = new Random();
        Slugify slugify = Slugify.builder().build();
        List<User>  users = userRepository.findByRole(UserRole.ADMIN);
        for (int i = 0; i < 20; i++) {
            User rdUser = users.get(random.nextInt(users.size()));

            boolean status = faker.bool().bool();
            Date createdAt = new Date();
            Date publishedAt = null;

            if (status) {
                publishedAt = createdAt;
            }
            String title = faker.book().title();
            Blog blog = Blog.builder()
                    .title(title)
                    .slug(slugify.slugify(title))
                    .description(faker.lorem().paragraph())
                    .content(faker.lorem().paragraph())
                    .status(status)
                    .thumbnail(generateLinkImage(title))
                    .createdAt(createdAt)
                    .updatedAt(createdAt)
                    .publishedAt(publishedAt)
                    .user(rdUser)
                    .build();
            blogRepository.save(blog);
        }
    }
    @Test
    void test_movie() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();

        List<Actor> actors = actorRepository.findAll();
        List<Director> directors = directorRepository.findAll();
        List<Genre> genres = genreRepository.findAll();

        for (int i = 0; i < 100; i++) {
            List<Genre> rdGenres = new ArrayList<>();
            for (int j = 0; j < faker.number().numberBetween(1, 5); j++) {
                Genre rdGenre = genres.get(faker.number().numberBetween(0, genres.size()));
                if (!rdGenres.contains(rdGenre)){
                    rdGenres.add(rdGenre);
                }
            }
            List<Actor> rdActors = new ArrayList<>();
            for (int j = 0; j < faker.number().numberBetween(1, 5); j++) {
                Actor rdActor = actors.get(faker.number().numberBetween(0, genres.size()));
                if (!rdActors.contains(rdActor)){
                    rdActors.add(rdActor);
                }
            }
            List<Director> rdDirectors = new ArrayList<>();
            for (int j = 0; j < faker.number().numberBetween(1, 5); j++) {
                Director rdDirector = directors.get(faker.number().numberBetween(0, genres.size()));
                if (!rdDirectors.contains(rdDirector)){
                    rdDirectors.add(rdDirector);
                }
            }

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
                    .genres(rdGenres)
                    .actors(rdActors)
                    .directors(rdDirectors)
                    .build();
            movieRepository.save(movie);
        }
    }
    @Test
    void test_review(){
        Faker faker = new Faker();
        List<User> users = userRepository.findAll();
        List<Movie> movies = movieRepository.findAll();
        Random random = new Random();
        for (Movie movie: movies) {
            for (int i = 0; i < random.nextInt(5) + 5; i++) {
                User rdUser = users.get(random.nextInt(users.size()));
                Review review = Review.builder()
                        .content(faker.lorem().paragraph())
                        .rating(faker.number().randomDouble(1, 1, 5))
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .user(rdUser)
                        .movie(movie)
                        .build();
                reviewRepository.save(review);
            }
        }
    }
    @Test
    void test_episode(){
        List<Movie> movieList = movieRepository.findAll();
        Random random = new Random();
        for (Movie movie: movieList) {
            if (movie.getType() == MovieType.PHIM_BO){
                for (int i = 0; i < random.nextInt(5) + 5; i++) {
                    Episode episode = Episode.builder()
                            .name("Tập " + (i+ 1))
                            .displayOrder(i + 1)
                            .status(true)
                            .createdAt(new Date())
                            .updatedAt(new Date())
                            .publishedAt(new Date())
                            .movie(movie)
                            .build();
                    episodeRepository.save(episode);
                }
            }else {
                Episode episode = Episode.builder()
                        .name("Tập full")
                        .displayOrder(1)
                        .status(true)
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .publishedAt(new Date())
                        .movie(movie)
                        .build();
                episodeRepository.save(episode);
            }
        }
    }
    private static String getCharacter(String str) {
        return str.substring(0, 1).toUpperCase();
    }
    public static String generateLinkImage(String name) {
        return "https://placehold.co/218x290?text=" + getCharacter(name);
    }

    @Test
    void testSQL(){
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Movie> moviePage = movieRepository.findByTypeAndStatus(MovieType.PHIM_BO, true, pageRequest);
        System.out.println(moviePage.getContent());
    }

}
