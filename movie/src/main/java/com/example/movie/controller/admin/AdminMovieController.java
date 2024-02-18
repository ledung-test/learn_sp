package com.example.movie.controller.admin;

import com.example.movie.entity.*;
import com.example.movie.service.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/movies")
public class AdminMovieController {
    @Autowired
    private AdminMovieService adminMovieService;
    @Autowired
    private AdminDirectorService adminDirectorService;
    @Autowired
    private AdminActorService adminActorService;
    @Autowired
    private AdminGenreService adminGenreService;
    @Autowired
    private AdminEpisodeService adminEpisodeService;

    @GetMapping
    public String getMovie(Model model) {
        List<Movie> movies = adminMovieService.getAllMovie();
        model.addAttribute("movies", movies);
        return "admin/movie/movie";
    }
    @GetMapping("/create")
    public String createMovie(Model model){
        setCommonAttributes(model);
        return "admin/movie/create";
    }
    @GetMapping("/{id}/detail")
    public String getDetailMovie(@PathVariable Integer id, Model model){
        Movie movie = adminMovieService.detailMovie(id);
        model.addAttribute("movie", movie);
        List<Episode> episodes = adminEpisodeService.getEpisodeListOfMovie(id);
        model.addAttribute("episodes", episodes);
        setCommonAttributes(model);
        return "admin/movie/detail";
    }
    private void setCommonAttributes(Model model) {
        List<Director> directors = adminDirectorService.getAllDirector();
        model.addAttribute("directors", directors);
        List<Actor> actors = adminActorService.getAllActor();
        model.addAttribute("actors", actors);
        List<Genre> genres = adminGenreService.getAllGenre();
        model.addAttribute("genres", genres);
    }

}