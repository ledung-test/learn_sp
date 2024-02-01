package com.example.movie.service.admin;

import com.example.movie.entity.*;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.model.enums.MovieType;
import com.example.movie.model.request.UpsertMovieRequest;
import com.example.movie.reponsitory.ActorRepository;
import com.example.movie.reponsitory.DirectorRepository;
import com.example.movie.reponsitory.GenreRepository;
import com.example.movie.reponsitory.MovieRepository;
import com.example.movie.service.FileService;
import com.example.movie.utils.FileUtils;
import com.github.slugify.Slugify;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminMovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private FileService fileService;
    @PersistenceContext
    private EntityManager entityManager;
    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    public Movie detailMovie(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phim có id: " + id));
    }
    public List<Director> addDirector(UpsertMovieRequest request){
        List<Director> directorList = new ArrayList<>();
        for (int i = 0; i < request.getDirectorIds().size(); i++) {
            int id = request.getGenreIds().get(i);
            Director director = directorRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đạo diễn có id: " + id));
            directorList.add(director);
        }
        return directorList;
    }
    public List<Actor> addActor(UpsertMovieRequest request){
        List<Actor> actorList = new ArrayList<>();
        for (int i = 0; i < request.getActorIds().size(); i++) {
            int id = request.getActorIds().get(i);
            Actor actor = actorRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy diễn viên có id: " + id));
            actorList.add(actor);
        }
        return actorList;
    }
    public List<Genre> addGenre(UpsertMovieRequest request){
        List<Genre> genreList = new ArrayList<>();
        for (int i = 0; i < request.getGenreIds().size(); i++) {
            int id = request.getGenreIds().get(i);
            Genre genre = genreRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thể loại có id: " + id));
            genreList.add(genre);
        }
        return genreList;
    }

    public Movie createMovie(UpsertMovieRequest request) {
        Slugify slugify = Slugify.builder().build();
        Movie movie = Movie.builder()
                .title(request.getTitle())
                .slug(slugify.slugify(request.getTitle()))
                .description(request.getDescription())
                .type(request.getType())
                .releaseYear(request.getReleaseYear())
                .status(request.getStatus())
                .genres(addGenre(request))
                .actors(addActor(request))
                .directors(addDirector(request))
                .build();
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Integer id, UpsertMovieRequest request) {
        Slugify slugify = Slugify.builder().build();
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phim có id: " + id));
        movie.setTitle(request.getTitle());
        movie.setSlug(slugify.slugify(request.getTitle()));
        movie.setDescription(request.getDescription());
        movie.setStatus(request.getStatus());
        movie.setType(request.getType());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setDirectors(addDirector(request));
        movie.setActors(addActor(request));
        movie.setGenres(addGenre(request));

        return movieRepository.save(movie);
    }

    @Transactional
    public void deleteMovie(Integer id) {
        Movie movie = entityManager.find(Movie.class, id);
        // Xóa tất cả các đánh giá liên quan đến bộ phim
        for (Review review : movie.getReviews()) {
            entityManager.remove(review);
        }
        // Xóa tất cả các liên kết với Directors
        movie.getDirectors().clear();
        // Xóa tất cả các liên kết với Actors
        movie.getActors().clear();
        // Xóa tất cả các liên kết với Genres
        movie.getGenres().clear();
        // Xóa bộ phim
        entityManager.remove(movie);
    }
    public String uploadThumbnail(Integer id, MultipartFile file) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phim có id: " + id));
        // Upload file vào trong thư mục trên server (image_uploads)
        if (movie.getPoster() != null){
            FileUtils.deleteFile(movie.getPoster());
        }
        String filePath = fileService.uploadFile(file);
        movie.setPoster(filePath);
        movieRepository.save(movie);
        return filePath;
    }

}
