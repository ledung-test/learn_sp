package com.example.movie.rest;

import com.example.movie.entity.Review;
import com.example.movie.model.request.UpsertReviewRequest;
import com.example.movie.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewResource {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody UpsertReviewRequest request) {
        Review review = reviewService.createReview(request);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@RequestBody UpsertReviewRequest request, @PathVariable Integer id){
        Review review = reviewService.updateReview(id, request);
        return ResponseEntity.ok(review);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id){
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

}
