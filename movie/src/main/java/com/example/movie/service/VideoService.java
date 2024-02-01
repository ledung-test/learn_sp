package com.example.movie.service;

import com.example.movie.exception.BadRequestException;
import com.example.movie.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;



@Slf4j
@Service
public class VideoService {
    public static final String UPLOAD_DIR = "video_upload";
    public static final long CHUNK_SIZE = 100000L;
    public VideoService(){
        FileUtils.createDirectory(UPLOAD_DIR);
    }

    public String uploadVideo(MultipartFile video) {
        if (video.isEmpty()){
            throw new BadRequestException("Không thể tải tệp rỗng");
        }
        // TODO: 30/01/2024 bổ xung logic
        //Validate file size, type, extension
        //Nén video
        //Trích xuất thông tin video: duration, format,...


        try {
            // Tạo tên tệp duy nhất với UUID
            String fileName = UUID.randomUUID().toString();
            // Tạo đường dẫn lưu tệp
            Path path = Paths.get(UPLOAD_DIR + File.separator + fileName);
            // Lưu tệp
            Files.copy(video.getInputStream(), path);
            return File.separator + "api" + UPLOAD_DIR + "videos" + File.separator + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể tải tệp lên");
        }
    }

    public ResourceRegion getVideoResourceRegion(String fileName, long start, long end) throws IOException {
        UrlResource videoResource = new UrlResource("file:" + UPLOAD_DIR + File.separator + fileName);

        if (!videoResource.exists() || !videoResource.isReadable()) {
            throw new IOException("Video not found");
        }

        Resource video = videoResource;
        long contentLength = video.contentLength();
        end = Math.min(end, contentLength - 1);

        long rangeLength = Math.min(CHUNK_SIZE, end - start + 1);
        return new ResourceRegion(video, start, rangeLength);
    }
}
