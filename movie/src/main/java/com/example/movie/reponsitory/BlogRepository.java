package com.example.movie.reponsitory;

import com.example.movie.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findAllByOrderByCreatedAtDesc();
    List<Blog> findByUserIdOrderByCreatedAtDesc(Integer userId);
    Blog findAllById(Integer id);
    Page<Blog> findByStatus(Boolean status, Pageable pageable);
    Blog findByStatusAndIdAndSlug(Boolean status, Integer id, String slug);
}
