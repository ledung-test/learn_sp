package com.example.movie.rest;

import com.example.movie.entity.Blog;
import com.example.movie.entity.Review;
import com.example.movie.model.request.UpsertBlogRequest;
import com.example.movie.model.request.UpsertReviewRequest;
import com.example.movie.service.BlogService;
import com.example.movie.service.admin.AdminBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/blogs")
@RequiredArgsConstructor
public class BlogResource {
    @Autowired
    private AdminBlogService adminBlogService;
    @PostMapping
    public ResponseEntity<?> createBlog(@RequestBody UpsertBlogRequest request) {
        Blog blog = adminBlogService.createBlog(request);
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(@RequestBody UpsertBlogRequest request, @PathVariable Integer id){
        Blog blog = adminBlogService.updateBlog(id, request);
        return ResponseEntity.ok(blog);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id){
        adminBlogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/upload-thumbnail")
    public ResponseEntity<?> uploadThumbnail(@RequestParam("file") MultipartFile file, @PathVariable Integer id) {
        String path = adminBlogService.uploadThumbnail(id, file);
        return ResponseEntity.ok(path);
    }
}
