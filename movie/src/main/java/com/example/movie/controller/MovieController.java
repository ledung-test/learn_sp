package com.example.movie.controller;


import com.example.movie.entity.Episode;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;
import com.example.movie.entity.User;
import com.example.movie.model.enums.MovieType;
import com.example.movie.reponsitory.EpisodeRepository;
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
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private HttpSession session;
    @GetMapping("/phim-bo")
    public String phimBo(Model model,
                            @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "12") Integer size){
        Page<Movie> pageData = movieService.findByTypeAndStatus(MovieType.PHIM_BO, true, page, size);
        User user = (User) session.getAttribute("currentUser");

        if (user != null){
            model.addAttribute("user", user);
        }
        model.addAttribute("pageData", pageData); // hiện thị dữ liệu phân trang
        model.addAttribute("currentPage", page); // active trang hiện tại

        return "web/phim-bo";
    }

    @GetMapping("/phim-le")
    public String phimLe(Model model,
                            @RequestParam(required = false, defaultValue = "1") Integer page,
                            @RequestParam(required = false, defaultValue = "12") Integer size){
        Page<Movie> pageData = movieService.findByTypeAndStatus(MovieType.PHIM_LE, true, page, size);
        User user = (User) session.getAttribute("currentUser");

        if (user != null){
            model.addAttribute("user", user);
        }
        model.addAttribute("pageData", pageData); // hiện thị dữ liệu phân trang
        model.addAttribute("currentPage", page); // active trang hiện tại

        return "web/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String phimChieuRap(Model model,
                                  @RequestParam(required = false, defaultValue = "1") Integer page,
                                  @RequestParam(required = false, defaultValue = "12") Integer size){
        Page<Movie> pageData = movieService.findByTypeAndStatus(MovieType.PHIM_CHIEU_RAP, true, page, size);
        User user = (User) session.getAttribute("currentUser");

        if (user != null){
            model.addAttribute("user", user);
        }
        model.addAttribute("pageData", pageData); // hiện thị dữ liệu phân trang
        model.addAttribute("currentPage", page); // active trang hiện tại

        return "web/phim-chieu-rap";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String chiTietPhim(Model model, @PathVariable Integer id, @PathVariable String slug){
        Movie movie = movieService.findByStatusAndIdAndSlug(true, id, slug);
        PageRequest pageRequest = PageRequest.of(1, 6);
        Page<Movie> deXuat = movieService.findByTypeAndStatusAndRatingGreaterThanEqualAndIdNotOrderByRatingDescViewDescPublishedAtDesc(
                movie.getType(), true, movie.getRating(), id, pageRequest);
        List<Review> reviewList = reviewService.findByMovie_IdOrderByCreatedAtDesc(id);
        User user = (User) session.getAttribute("currentUser");
        List<Episode> episodes = episodeService.getEpisodeListOfMovie(id, true);

        if (user != null){
            model.addAttribute("user", user);
        }
        model.addAttribute("chiTietPhim", movie);
        model.addAttribute("deXuat", deXuat);
        model.addAttribute("reviews", reviewList);
        model.addAttribute("episodes", episodes);

        return "web/chi-tiet-phim";
    }
}
