package com.example.movie.controller;

import com.example.movie.entity.Blog;
import com.example.movie.entity.Movie;
import com.example.movie.model.enums.MovieType;
import com.example.movie.service.BlogService;
import com.example.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WebController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private BlogService blogService;
    @GetMapping("/")
    public String trangChu(Model model){
        Page<Movie> deXuat = movieService.findByStatus(true, 1, 6);
        Page<Movie> phimBo = movieService.findByTypeAndStatus(MovieType.PHIM_BO, true, 1, 12);
        Page<Movie> phimLe = movieService.findByTypeAndStatus(MovieType.PHIM_LE, true, 1, 12);
        Page<Blog> blogs = blogService.findByStatus(true, 1, 4);
        model.addAttribute("deXuat", deXuat);
        model.addAttribute("phimBo", phimBo);
        model.addAttribute("phimLe", phimLe);
        model.addAttribute("blogs", blogs);
        return "web/trang-chu";
    }
}
