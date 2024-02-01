package com.example.movie.rest;

import com.example.movie.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
public class VideoResource {
    @Autowired
    private VideoService videoService;

    @PostMapping
    public ResponseEntity<?> createVideo(@RequestParam("video")MultipartFile video){
        String path = videoService.uploadVideo(video);
        return ResponseEntity.ok(path);
    }
}
