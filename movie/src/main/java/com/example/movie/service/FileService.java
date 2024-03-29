package com.example.movie.service;

import com.example.movie.exception.BadRequestException;
import com.example.movie.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {
    public static final String UPLOAD_DIR = "image_upload";
    public FileService(){
        FileUtils.createDirectory(UPLOAD_DIR);
    }

    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BadRequestException("Không có tệp để tải lên");
        }
        try {
            // Tạo tên tệp duy nhất với UUID
            String fileName = UUID.randomUUID().toString();
            // Tạo đường dẫn lưu tệp
            Path path = Paths.get(UPLOAD_DIR + File.separator + fileName);
            // Lưu tệp
            Files.copy(file.getInputStream(), path);
            return File.separator + UPLOAD_DIR + File.separator + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể tải tệp lên");
        }
    }
}
