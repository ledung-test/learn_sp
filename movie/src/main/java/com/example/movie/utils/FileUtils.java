package com.example.movie.utils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileUtils {
    public static void createDirectory(String path) {
        File directory = new File(path);
        // Kiểm tra xem thư mục đã tồn tại chưa
        if (!directory.exists()) {
            boolean result = directory.mkdirs(); // Tạo thư mục mới
            // Kiểm tra xem thư mục đã được tạo thành công không
            if (result) {
                System.out.println("Thư mục đã được tạo thành công: " + path);
            } else {
                System.out.println("Không thể tạo thư mục: " + path);
            }
        } else {
            System.out.println("Thư mục đã tồn tại: " + path);
        }
    }
    public static void deleteFile(String filePath) {
        log.info("Xóa file: " + filePath);
        // filePath: /image_uploads/123456789
        filePath = filePath.substring(1);
        Path path = Paths.get(filePath);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            log.error("Không thể xóa file");
            log.error(e.getMessage());
            throw new RuntimeException("Could not delete file");
        }
    }
}
