package com.example.movie.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String title;
    String slug;
    @Column(columnDefinition = "TEXT")
    String description;
    @Column(columnDefinition = "TEXT")
    String content;
    String thumbnail;
    Boolean status;
    Date createdAt;
    Date updatedAt;
    Date publishedAt;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    @PrePersist // Trước khi lưu dữ liệu vào database
    public void prePersist() {
        createdAt = new Date();
        updatedAt = new Date();
        if (status){
            publishedAt = new Date();
        }
    }

    @PreUpdate // Trước khi cập nhật dữ liệu vào database
    public void preUpdate() {
        updatedAt = new Date();
        if (status){
            publishedAt = new Date();
        }else {
            publishedAt = null;
        }
    }

}
