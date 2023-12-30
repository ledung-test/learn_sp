package com.example.mini_pr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class MiniPrApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniPrApplication.class, args);
        Path projectRoot = Paths.get("").toAbsolutePath();

        // Kết hợp với đường dẫn tương đối đến thư mục static
        Path staticPath = projectRoot.resolve("static");

        System.out.println("Absolute Path to 'static' folder: " + staticPath);
    }

}
