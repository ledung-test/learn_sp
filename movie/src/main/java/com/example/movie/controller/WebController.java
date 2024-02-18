package com.example.movie.controller;

import com.example.movie.entity.*;

import com.example.movie.model.enums.MovieType;
import com.example.movie.service.BlogService;
import com.example.movie.service.EpisodeService;
import com.example.movie.service.MovieService;
import com.example.movie.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Autowired
    private BlogService blogService;
    @Autowired
    private HttpSession session;
    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String trangChu(Model model){
        Page<Movie> deXuat = movieService.findByStatus(true, 1, 6);
        Page<Movie> phimBo = movieService.findByTypeAndStatus(MovieType.PHIM_BO, true, 1, 12);
        Page<Movie> phimLe = movieService.findByTypeAndStatus(MovieType.PHIM_LE, true, 1, 12);
        Page<Blog> blogs = blogService.findByStatus(true, 1, 4);
        User user = (User) session.getAttribute("currentUser");

        if (user != null){
            model.addAttribute("user", user);
        }
        model.addAttribute("deXuat", deXuat);
        model.addAttribute("phimBo", phimBo);
        model.addAttribute("phimLe", phimLe);
        model.addAttribute("blogs", blogs);

        return "web/trang-chu";
    }

    @GetMapping("/dang-nhap")
    public String dangNhap(){
        return "web/dang-nhap";
    }
    @GetMapping("/dang-ky")
    public String dangKy(){
        return "web/dang-ky";
    }

    @GetMapping("/xem-phim/{id}/{slug}")
    public String getXemPhimPage(Model model, @PathVariable Integer id, @PathVariable String slug, @RequestParam String tap){
        Movie movie = movieService.findByStatusAndIdAndSlug(true, id, slug);
        PageRequest pageRequest = PageRequest.of(1, 6);
        Page<Movie> deXuat = movieService.findByTypeAndStatusAndRatingGreaterThanEqualAndIdNotOrderByRatingDescViewDescPublishedAtDesc(
                movie.getType(), true, movie.getRating(), id, pageRequest);
        List<Episode> episodes = episodeService.getEpisodeListOfMovie(id, true);
        Episode currentEpisode = episodeService.getCurrentEpisode(id, tap, true);
        List<Review> reviewList = reviewService.findByMovie_IdOrderByCreatedAtDesc(id);
        User user = (User) session.getAttribute("currentUser");

        if (user != null){
            model.addAttribute("user", user);
        }
        model.addAttribute("chiTietPhim", movie);
        model.addAttribute("deXuat", deXuat);
        model.addAttribute("reviews", reviewList);
        model.addAttribute("episodes", episodes);
        model.addAttribute("currEpisode", currentEpisode);

        return "web/xem-phim";
    }
}
