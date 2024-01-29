package com.example.movie.rest;

import com.example.movie.entity.Director;
import com.example.movie.model.request.UpsertDirectorRequest;
import com.example.movie.service.admin.AdminDirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/directors")
public class DirectorResource {
    @Autowired
    private AdminDirectorService directorService;
    @PostMapping
    public ResponseEntity<?> createDirector(@RequestBody UpsertDirectorRequest request) {
        Director director = directorService.createDirector(request);
        return new ResponseEntity<>(director, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDirector(@RequestBody UpsertDirectorRequest request, @PathVariable Integer id) {
        Director director = directorService.updateDirector(request, id);
        return new ResponseEntity<>(director, HttpStatus.CREATED);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteDirector(@PathVariable Integer id){
        directorService.deleteDirector(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}/upload-thumbnail")
    public ResponseEntity<?> uploadThumbnail(@RequestParam("file") MultipartFile file, @PathVariable Integer id) {
        String path = directorService.uploadThumbnailDirector(id, file);
        return ResponseEntity.ok(path);
    }
}
