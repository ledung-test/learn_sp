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
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(columnDefinition = "TEXT")
    String content;
    Double rating;

    @Transient // không tạo cột mới trong database
    String ratingText;
    Date createdAt;
    Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getRatingText() {
        double doubleValue = rating;
        int intValue = (int) doubleValue;
        return switch (intValue) {
            case 1 -> "Tệ";
            case 2 -> "Không hay";
            case 3 -> "Bình thường";
            case 4 -> "Hay";
            case 5 -> "Tuyệt vời";
            default -> "Chưa có đánh giá";
        };
    }
}
