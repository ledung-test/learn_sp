package com.example.movie.rest;
import com.example.movie.entity.Genre;
import com.example.movie.model.request.UpsertGenreRequest;
import com.example.movie.service.admin.AdminGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/genres")
public class GenreResource {
    @Autowired
    private AdminGenreService adminGenreService;
    @PostMapping
    public ResponseEntity<?> createGenre(@RequestBody UpsertGenreRequest request){
        Genre genre = adminGenreService.createGenre(request);
        return new ResponseEntity<>(genre, HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGenre(@RequestBody UpsertGenreRequest request, @PathVariable Integer id) {
        Genre genre = adminGenreService.updateGenre(request, id);
        return new ResponseEntity<>(genre, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable Integer id){
        adminGenreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
