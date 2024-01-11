package com.example.movie.service;

import com.example.movie.entity.Movie;
import com.example.movie.model.enums.MovieType;
import com.example.movie.reponsitory.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findByStatusAndType(Boolean status, MovieType type, Sort sort){
        return movieRepository.findByStatusAndType(status, type, sort);
    }

    public Movie findByIdAndSlug(Boolean status, Integer id, String slug){
        return movieRepository.findByStatusAndIdAndSlug(status,id, slug);
    }

}
