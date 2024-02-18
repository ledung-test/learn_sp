package com.example.movie.rest;

import com.example.movie.entity.Actor;
import com.example.movie.entity.Director;
import com.example.movie.model.request.UpsertActorRequest;
import com.example.movie.model.request.UpsertDirectorRequest;
import com.example.movie.service.admin.AdminActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/actors")
public class ActorResource {
    @Autowired
    private AdminActorService adminActorService;
    @PostMapping
    public ResponseEntity<?> createDirector(@RequestBody UpsertActorRequest request) {
        Actor actor = adminActorService.createActor(request);
        return new ResponseEntity<>(actor, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateActor(@RequestBody UpsertActorRequest request, @PathVariable Integer id) {
        Actor actor = adminActorService.updateActor(request, id);
        return new ResponseEntity<>(actor, HttpStatus.CREATED);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable Integer id){
        adminActorService.deleteActor(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}/upload-thumbnail")
    public ResponseEntity<?> uploadThumbnail(@RequestParam("file") MultipartFile file, @PathVariable Integer id) {
        String path = adminActorService.uploadThumbnailActor(id, file);
        return ResponseEntity.ok(path);
    }
}
