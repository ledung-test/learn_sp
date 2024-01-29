package com.example.movie.reponsitory;

import com.example.movie.entity.Director;
import com.example.movie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
    Page<Director> findAll(Pageable pageable);
}
