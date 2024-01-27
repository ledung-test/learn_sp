package com.example.movie.service;

import com.example.movie.entity.Blog;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.reponsitory.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private FileService fileService;
    public Page<Blog> findByStatus(Boolean status, Integer page, Integer size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("publishedAt").descending());
        return blogRepository.findByStatus(status, pageRequest);
    }
    public Blog findByStatusAndIdAndSlug(Boolean status, Integer id, String slug){
        return blogRepository.findByStatusAndIdAndSlug(status, id, slug);
    }
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
