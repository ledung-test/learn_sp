package com.example.movie.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/blogs")
    public String getBlog(){
        return "admin/blog/blog";
    }
    @GetMapping("/blogs/own-blogs")
    public String getOwnBlogs(){
        return "admin/blog/own-blog";
    }
    @GetMapping("/blogs/create")
    public String getCreateBlog(){
        return "admin/blog/create";
    }
    @GetMapping("blogs/{id}/detail")
    public String getDetailBlog(@PathVariable Integer id){
        return "admin/blog/detail";
    }

}
