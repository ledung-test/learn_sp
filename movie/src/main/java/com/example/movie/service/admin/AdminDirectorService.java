package com.example.movie.service.admin;


import com.example.movie.entity.Director;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.model.request.UpsertDirectorRequest;
import com.example.movie.reponsitory.DirectorRepository;
import com.example.movie.service.FileService;
import com.example.movie.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AdminDirectorService {
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private FileService fileService;

    //Lấy tất danh sách tất cả các đạo diễn
    public List<Director> getAllDirector(){
        return  directorRepository.findAll();
    }
    //Thêm mới một đạo diễn
    public Director createDirector(UpsertDirectorRequest request) {
        Director director = Director.builder()
                .name(request.getNameDr())
                .birthday(request.getBirthdayDr())
                .description(request.getDescriptionDr())
                .build();
        return directorRepository.save(director);
    }
    //Cập nhật thông tin đạo diễn
    public Director updateDirector(UpsertDirectorRequest request, Integer id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đạo diễn có id: " + id));
        director.setName(request.getNameDr());
        director.setBirthday(request.getBirthdayDr());
        director.setDescription(request.getDescriptionDr());
        return directorRepository.save(director);
    }
    //Xóa một đạo diễn
    public void deleteDirector(Integer id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đạo diễn có id: " + id));
        if (director.getAvatar() != null){
            FileUtils.deleteFile(director.getAvatar());
        }
        directorRepository.delete(director);
    }
    //Lấy chi tiết đạo diễn theo id
    public Director detailDirector(Integer id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đạo diễn có id: " + id));
    }
    //Upload avatar đạo diễn
    public String uploadThumbnailDirector(Integer id, MultipartFile file) {
        // Kỉem tra xem đạo diễn có tồn tại không
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đạo diễn có id: " + id));
        // Upload file vào trong thư mục trên server (image_uploads)
        String filePath = fileService.uploadFile(file);
        // Cập nhật đường dẫn thumbnail cho đạo diễn
        director.setAvatar(filePath);
        directorRepository.save(director);
        return filePath;
    }


}
