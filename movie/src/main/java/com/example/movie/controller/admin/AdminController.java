package com.example.movie.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("")
    public String getAdmin(){
        return "admin/index";
    }
    @GetMapping("/blog")
    public String getBlog(){
        return "admin/blog/blog";
    }
    @GetMapping("/blog/own-blogs")
    public String getOwnBlogs(){
        return "admin/blog/own-blog";
    }
    @GetMapping("/blog/create")
    public String getCreateBlog(){
        return "admin/blog/create";
    }
    @GetMapping("/blog/detail")
    public String getDetailBlog(){
        return "admin/blog/detail";
    }

}
