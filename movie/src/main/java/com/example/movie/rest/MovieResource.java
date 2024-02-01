package com.example.movie.rest;


import com.example.movie.entity.Movie;
import com.example.movie.model.request.UpsertMovieRequest;
import com.example.movie.service.admin.AdminMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/movies")
public class MovieResource {
    @Autowired
    private AdminMovieService adminMovieService;

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody UpsertMovieRequest request){
        Movie movie = adminMovieService.createMovie(request);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@RequestBody UpsertMovieRequest request, @PathVariable Integer id){
        Movie movie = adminMovieService.updateMovie(id, request);
        return ResponseEntity.ok(movie);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id){
        adminMovieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}/upload-thumbnail")
    public ResponseEntity<?> uploadThumbnail(@RequestParam("file") MultipartFile file, @PathVariable Integer id) {
        String path = adminMovieService.uploadThumbnail(id, file);
        return ResponseEntity.ok(path);
    }

}
