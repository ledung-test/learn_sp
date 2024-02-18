package com.example.movie.service.admin;

import com.example.movie.entity.Actor;
import com.example.movie.entity.Director;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.model.request.UpsertActorRequest;
import com.example.movie.model.request.UpsertDirectorRequest;
import com.example.movie.reponsitory.ActorRepository;
import com.example.movie.service.FileService;
import com.example.movie.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AdminActorService {
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private FileService fileService;
    public List<Actor> getAllActor(){
        return actorRepository.findAll();
    }

    //Lấy chi tiết diễn viên
    public Actor detailActor(Integer id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy diễn viên có id: " + id));
    }

    //Thêm mới một diễn viên
    public Actor createActor(UpsertActorRequest request) {
        Actor actor = Actor.builder()
                .name(request.getNameAt())
                .birthday(request.getBirthdayAt())
                .description(request.getDescriptionAt())
                .build();
        return actorRepository.save(actor);
    }
    //Cập nhật thông tin diễn viên
    public Actor updateActor(UpsertActorRequest request, Integer id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy diễn viên có id: " + id));
        actor.setName(request.getNameAt());
        actor.setBirthday(request.getBirthdayAt());
        actor.setDescription(request.getDescriptionAt());
        return actorRepository.save(actor);
    }
    //Xóa một diễn viên
    public void deleteActor(Integer id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy diễn viên có id: " + id));
        if (actor.getAvatar() != null){
            FileUtils.deleteFile(actor.getAvatar());
        }
        actorRepository.delete(actor);
    }
    //Upload avatar diễn viên
    public String uploadThumbnailActor(Integer id, MultipartFile file) {
        // Kỉem tra xem đạo diễn có tồn tại không
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy diễn viên có id: " + id));
        //Upload file vào trong thư mục trên server (image_uploads)
        if (actor.getAvatar() != null){
            FileUtils.deleteFile(actor.getAvatar());
        }
        String filePath = fileService.uploadFile(file);
        // Cập nhật đường dẫn thumbnail
        actor.setAvatar(filePath);
        actorRepository.save(actor);
        return filePath;
    }
}
