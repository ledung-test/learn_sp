package com.example.movie.service;

import com.example.movie.entity.Blog;
import com.example.movie.reponsitory.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    public Page<Blog> findByStatus(Boolean status, Integer page, Integer size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("publishedAt").descending());
        return blogRepository.findByStatus(status, pageRequest);
    }
    public Blog findByStatusAndIdAndSlug(Boolean status, Integer id, String slug){
        return blogRepository.findByStatusAndIdAndSlug(status, id, slug);
    }
}
