package com.example.movie.controller.admin;

import com.example.movie.entity.Actor;
import com.example.movie.entity.Genre;
import com.example.movie.service.admin.AdminGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/genres")
public class AdminGenreController {
    @Autowired
    private AdminGenreService adminGenreService;

    @GetMapping
    public String getGenre(Model model){
        List<Genre> genres  = adminGenreService.getAllGenre();
        model.addAttribute("genres", genres);
        return "admin/genre/genre";
    }
    @GetMapping("/create")
    public String getCreateGenre(){
        return "admin/genre/create";
    }
    @GetMapping("/{id}/detail")
    public String getDetailGenre(Model model, @PathVariable Integer id){
        Genre genre = adminGenreService.getDetialGenre(id);
        model.addAttribute("genre", genre);
        return "admin/genre/detail";
    }
}
