package com.example.movie.rest;

import com.example.movie.service.admin.AdminEpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/episodes")
public class EpisodeResource {
    @Autowired
    private AdminEpisodeService adminEpisodeService;
    @PostMapping("/{id}/upload-video")
    public ResponseEntity<?> uploadVideo(@RequestParam("video")MultipartFile file, @PathVariable Integer id){
        adminEpisodeService.uploadFile(file, id);
        return ResponseEntity.ok().build();
    }
}
