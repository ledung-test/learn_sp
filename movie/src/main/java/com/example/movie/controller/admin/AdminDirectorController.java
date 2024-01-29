package com.example.movie.controller.admin;

import com.example.movie.entity.Blog;
import com.example.movie.entity.Director;
import com.example.movie.service.admin.AdminDirectorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/admin/directors")
public class AdminDirectorController {
    @Autowired
    private AdminDirectorService adminDirectorService;
    @GetMapping
    public String getDirector(Model model){
        List<Director> directors = adminDirectorService.getAllDirector();
        model.addAttribute("directors", directors);
        return "admin/director/director";
    }
    @GetMapping("/create")
    public String createDirector(){
        return "admin/director/create";
    }
    @GetMapping("/{id}/detail")
    public String detailDirector(Model model, @PathVariable Integer id){
        Director director = adminDirectorService.detailDirector(id);
        model.addAttribute("director", director);
        return "admin/director/detail";
    }
}
