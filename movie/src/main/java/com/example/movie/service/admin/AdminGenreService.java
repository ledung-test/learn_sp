package com.example.movie.service.admin;

import com.example.movie.entity.Genre;
import com.example.movie.reponsitory.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminGenreService {
    @Autowired
    private GenreRepository genreRepository;
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }
}
