package com.example.movie.controller;

import com.example.movie.entity.Movie;
import com.example.movie.model.enums.MovieType;
import com.example.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {
    @Autowired
    private MovieService movieService;
    @GetMapping("/")
    public String getTrangChu(Model model){
        Page<Movie> deXuat = movieService.findByStatus(true, 1, 6);
        Page<Movie> phimBo = movieService.findByTypeAndStatus(MovieType.PHIM_BO, true, 1, 12);
        Page<Movie> phimLe = movieService.findByTypeAndStatus(MovieType.PHIM_LE, true, 1, 12);
        model.addAttribute("deXuat", deXuat);
        model.addAttribute("phimBo", phimBo);
        model.addAttribute("phimLe", phimLe);
        return "web/trang-chu";
    }

    //phim-bo?page=10&size=12
    //phim-bo -> page = 1, size = 12
    @GetMapping("/phim-bo")
    public String getPhimBo(Model model,
                            @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "10") Integer size){
        Page<Movie> pageData = movieService.findByTypeAndStatus(MovieType.PHIM_BO, true, page, size);
        model.addAttribute("pageData", pageData); // hiện thị dữ liệu phân trang
        model.addAttribute("currentPage", page); // active trang hiện tại
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
