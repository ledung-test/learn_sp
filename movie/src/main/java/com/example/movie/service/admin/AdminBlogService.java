package com.example.movie.service.admin;

import com.example.movie.entity.Blog;
import com.example.movie.entity.User;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.model.request.UpsertBlogRequest;

import com.example.movie.reponsitory.BlogRepository;
import com.example.movie.service.FileService;
import com.example.movie.utils.FileUtils;
import com.github.slugify.Slugify;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AdminBlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private HttpSession session;
    //Lấy tất cả các bài blog sắp xếp theo thời gian mới nhất
    public List<Blog> findAllByOrderByCreatedAtDesc() {
        return blogRepository.findAllByOrderByCreatedAtDesc();
    }
    //Lấy tất cả bài viết của tài khoản đang đăng nhập
    public List<Blog> findByUserIdOrderByCreatedAtDesc(){
        User user = (User) session.getAttribute("currentUser");
        int userId = user.getId();
        return blogRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
    //Thêm mới blog
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
    //Cập nhật blog
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
    //Xóa một blog
    public void deleteBlog(Integer id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bài viết với id = " + id));
        // Kiểm tra xem blog có thumbnail không. Nếu có thì xóa file thumbnail
        if (blog.getThumbnail() != null) {
            FileUtils.deleteFile(blog.getThumbnail());
        }

        blogRepository.delete(blog);
    }
    //Chi tiết blog theo id
    public Blog detailBlog(Integer id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy blog có id: " + id));
    }
    //Upload ảnh blog
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
}
