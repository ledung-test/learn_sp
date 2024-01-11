package com.example.movie.controller;

import com.example.movie.entity.Movie;
import com.example.movie.model.enums.MovieType;
import com.example.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class WebController {
    @Autowired
    private MovieService movieService;
    @GetMapping("/phim-bo")
    public String getPhimBo(Model model){
        List<Movie> phimBo = movieService.findByStatusAndType(true, MovieType.PHIM_BO, Sort.by("publishedAt").descending());
        model.addAttribute("phimBo", phimBo);
        return "web/phim-bo";
    }

    @GetMapping("/phim-le")
    public String getPhimLe(Model model){
        List<Movie> phimLe = movieService.findByStatusAndType(true, MovieType.PHIM_LE, Sort.by("publishedAt").descending());
        model.addAttribute("phimLe", phimLe);
        return "web/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRap(Model model){
        List<Movie> phimChieuRap = movieService.findByStatusAndType(true, MovieType.PHIM_CHIEU_RAP, Sort.by("publishedAt").descending());
        model.addAttribute("phimChieuRap", phimChieuRap);
        return "web/phim-chieu-rap";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String getPhim(Model model, @PathVariable Integer id, @PathVariable String slug){
        Movie movie = movieService.findByIdAndSlug(true, id, slug);
        model.addAttribute("chiTietPhim", movie);
        return "web/chi-tiet-phim";
    }



}
