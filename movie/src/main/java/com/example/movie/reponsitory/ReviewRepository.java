package com.example.movie.reponsitory;

import com.example.movie.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByMovie_IdOrderByCreatedAtDesc(Integer movieId);
}
