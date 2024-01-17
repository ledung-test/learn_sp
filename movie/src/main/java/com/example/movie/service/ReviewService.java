package com.example.movie.service;

import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;
import com.example.movie.entity.User;
import com.example.movie.exception.BadRequestException;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.model.request.UpsertReviewRequest;
import com.example.movie.reponsitory.MovieRepository;
import com.example.movie.reponsitory.ReviewRepository;
import com.example.movie.reponsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;

    public List<Review> findByMovie_IdOrderByCreatedAtDesc(Integer movieId) {
        return reviewRepository.findByMovie_IdOrderByCreatedAtDesc(movieId);
    }


    public Review createReview(UpsertReviewRequest request) {
        // TODO: Giả định current user là user có id = 1. Sau này current user sẽ là user đang login
        Integer currentUserId = 1;
        User currentUser = userRepository.findById(currentUserId) // Kiểm tra xem user có tồn tại không
                .orElseThrow(() -> new ResourceNotFoundException("User không tồn tại"));

        Movie movie = movieRepository.findById(request.getMovieId()) // Kiểm tra xem movie có tồn tại không
                .orElseThrow(() -> new ResourceNotFoundException("Phim không tồn tại"));

        // Tạo review
        Review review = Review.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .movie(movie)
                .user(currentUser)
                .build();

        // Lưu review vào database
        return reviewRepository.save(review);
    }

    public Review updateReview(Integer id, UpsertReviewRequest upsertReviewRequest) {
        Integer currentUserId = 1;
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review không tồn tại"));
        User currentUser = userRepository.findById(currentUserId).orElseThrow(() -> new ResourceNotFoundException("User không tồn tại"));
        Movie currentMovie = movieRepository.findById(upsertReviewRequest.getMovieId()).orElseThrow(() -> new ResourceNotFoundException("Movie không tồn tại"));
        if (!review.getUser().getId().equals(currentUser.getId())){
            throw new BadRequestException("Bạn không có quyền sửa review này");
        }
        if (!review.getMovie().getId().equals(currentMovie.getId())){
            throw new BadRequestException(("Review không thuộc phim này"));
        }
        review.setContent(upsertReviewRequest.getContent());
        review.setRating(upsertReviewRequest.getRating());

        return reviewRepository.save(review);
    }

    public void deleteReview(Integer id) {
        Integer currentUserId = 1;
        User currentUser = userRepository.findById(currentUserId).orElseThrow(() -> new ResourceNotFoundException("User không tồn tại"));
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review không tồn tại"));
        if (!review.getUser().getId().equals(currentUser.getId())){
            throw new BadRequestException("Bạn không có quyền sửa review này");
        }
        reviewRepository.delete(review);
    }
}
