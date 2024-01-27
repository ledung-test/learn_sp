package com.example.movie.service.admin;

import com.example.movie.entity.Blog;
import com.example.movie.entity.User;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.model.request.UpsertBlogRequest;
import com.example.movie.model.request.UpsertReviewRequest;
import com.example.movie.reponsitory.BlogRepository;
import com.example.movie.service.FileService;
import com.github.slugify.Slugify;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class AdminBlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private HttpSession session;
    public List<Blog> findAllByOrderByCreatedAtDesc() {
        return blogRepository.findAllByOrderByCreatedAtDesc();
    }
    public List<Blog> findByUserIdOrderByCreatedAtDesc(Integer id){
        return blogRepository.findByUserIdOrderByCreatedAtDesc(id);
    }

    public Blog findAllById(Integer id){
        return blogRepository.findAllById(id);
    }

    // Upload thumbnail
    // C1: Upload file vào trong database
    // C2: Upload file vào trong thư mục trên server (image_uploads)
    public String uploadThumbnail(Integer id, MultipartFile file) {
        // Kỉem tra xem bài viết có tồn tại không
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bài viết có id: " + id));
        // Upload file vào trong thư mục trên server (image_uploads)
        String filePath = fileService.uploadFile(file);
        // Cập nhật đường dẫn thumbnail cho bài viết
        blog.setThumbnail(filePath);
        blogRepository.save(blog);
        return filePath;
    }
    public void deleteBlog(Integer id) {
        blogRepository.deleteById(id);
    }
    public Blog updateBlog(Integer id, UpsertBlogRequest request) {
        User currentUser = (User) session.getAttribute("currentUser");
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bài viết có id: " + id));
        Slugify slugify = Slugify.builder().build();
        blog.setTitle(request.getTitle());
        blog.setSlug(slugify.slugify(request.getTitle()));
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setStatus(request.getStatus());
        blog.setUser(currentUser);
        return blogRepository.save(blog);
    }

    public Blog createBlog(UpsertBlogRequest request) {
        User currentUser = (User) session.getAttribute("currentUser");
        Slugify slugify = Slugify.builder().build();
        Blog blog = Blog.builder()
                .title(request.getTitle())
                .slug(slugify.slugify(request.getTitle()))
                .description(request.getDescription())
                .content(request.getContent())
                .status(request.getStatus())
                .user(currentUser)
                .build();

        return blogRepository.save(blog);
    }
}
