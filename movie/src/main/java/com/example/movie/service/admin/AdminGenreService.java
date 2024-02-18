package com.example.movie.service.admin;

import com.example.movie.entity.Genre;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.model.request.UpsertGenreRequest;
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

    //Tạo mới thể loại
    public Genre createGenre(UpsertGenreRequest request) {
        Genre genreExist = genreRepository.findByName(request.getNameGenre());
        if (genreExist != null){
            throw new ResourceNotFoundException("Tên thể loại đã tồn tại");
        }
        Genre genre = Genre.builder()
                .name(request.getNameGenre())
                .build();
        return genreRepository.save(genre);
    }
    //Chi tiết thể loại theo id
    public Genre getDetialGenre(Integer id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thể loại có id: " + id));
    }

    public Genre updateGenre(UpsertGenreRequest request, Integer id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thể loại có id: " + id));
        genre.setName(request.getNameGenre());
        return genreRepository.save(genre);
    }

    public void deleteGenre(Integer id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thể loại có id: " + id));
        genreRepository.delete(genre);
    }
}
