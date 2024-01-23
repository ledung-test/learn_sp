package com.example.movie.controller;

import com.example.movie.entity.Blog;
import com.example.movie.entity.Movie;
import com.example.movie.entity.User;
import com.example.movie.service.BlogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private HttpSession session;

    @GetMapping("/tin-tuc")
    public String tinTuc(Model model,
                                  @RequestParam(required = false, defaultValue = "1") Integer page,
                                  @RequestParam(required = false, defaultValue = "6") Integer size){
        Page<Blog> pageData = blogService.findByStatus(true, page, size);
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        User user = (User) session.getAttribute("currentUser");
        if (user != null){
            model.addAttribute("user", user);
        }
        return "web/tin-tuc";
    }

    @GetMapping("/tin-tuc/{id}/{slug}")
    public String chiTietTinTuc(Model model, @PathVariable Integer id, @PathVariable String slug){
        Blog blog = blogService.findByStatusAndIdAndSlug(true, id, slug);
        model.addAttribute("chiTietTinTuc", blog);
        User user = (User) session.getAttribute("currentUser");
        if (user != null){
            model.addAttribute("user", user);
        }
        return "web/chi-tiet-tin-tuc";
    }
}
