package com.example.movie.controller.admin;

import com.example.movie.entity.Blog;
import com.example.movie.entity.User;
import com.example.movie.service.admin.AdminBlogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/blogs")
public class AdminBlogController {
    @Autowired
    private AdminBlogService adminBlogService;
    @Autowired
    private HttpSession session;
    @GetMapping("")
    public String getBlog(Model model){
        //Lấy tất cả bài viết sắp xếp theo created giảm dần
        List<Blog> blogList = adminBlogService.findAllByOrderByCreatedAtDesc();
        model.addAttribute("blogs", blogList);
        return "admin/blog/blog";
    }
    @GetMapping("/own-blogs")
    public String getOwnBlogs(Model model){
        //Lấy tất cả bài viết sắp xếp theo created giảm dần thuộc về 1 user
        List<Blog> blogList = adminBlogService.findByUserIdOrderByCreatedAtDesc();
        model.addAttribute("blogsUser", blogList);
        return "admin/blog/own-blog";
    }
    @GetMapping("/create")
    public String getCreateBlog(){
        return "admin/blog/create";
    }
    @GetMapping("/{id}/detail")
    public String getDetailBlog(@PathVariable Integer id, Model model){
        //Lấy bài viết theo id
        Blog blog = adminBlogService.detailBlog(id);
        model.addAttribute("blog", blog);
        return "admin/blog/detail";
    }
}
