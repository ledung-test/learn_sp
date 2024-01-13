package com.example.movie.reponsitory;

import com.example.movie.entity.Movie;
import com.example.movie.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//Movie: đối tượng thao tác
//Integer: Kiểu dũ liệu của khóa chính
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    //Tìm kiếm phim theo status, theo type, publishAT
    List<Movie> findByStatusAndType(Boolean status, MovieType type, Sort sort);

    Movie findByStatusAndIdAndSlug(Boolean status, Integer id, String slug);

    //SELECT * FROM movies ORDER BY publishedAt DESC LIMIT 10 OFFSET 10

    //Phân trang dùng Pageable
    Page<Movie> findByTypeAndStatus(MovieType type, Boolean status, Pageable pageable);

    //Phim đề xuất
    Page<Movie> findByStatus(Boolean status, Pageable pageable);



    // Tìm kiếm phim theo tiêu đề chứa từ khóa
    //JPQL Query
    //@Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
    //Native SQL
    @Query(value = "SELECT * FROM movies WHERE title LIKE %:title%", nativeQuery = true)
    List<Movie> findByTitleContaining(@Param("title") String title);

    // Kiểm tra phim có tồn tại theo tiêu đề
    //JPQL Query
    //@Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Movie m WHERE m.title = :title")
    //boolean existsByTitle(@Param("title") String title);
    //Native SQL
    @Query(value = "SELECT EXISTS (SELECT * FROM movies WHERE title = :title)", nativeQuery = true)
    Integer existsByTitle(@Param("title") String title);

    // Đếm số lượng phim theo tiêu đề
    //JPQL Query
    //@Query("SELECT COUNT(m) FROM Movie m WHERE m.title = :title")
    //Native SQL
    @Query(value = "SELECT COUNT(*) FROM movies WHERE title = :title", nativeQuery = true)
    long countByTitle(@Param("title") String title);

    // Tìm movie theo type và status. Sau đó sắp xếp theo publishedAt giảm dần
    //JPQL Query
    @Query("SELECT m FROM Movie m WHERE m.type = :type AND m.status = :status ORDER BY m.publishedAt DESC")
    //Native SQL
    //@Query(value = "SELECT * FROM movies WHERE type = :type AND status = :status ORDER BY published_at DESC", nativeQuery = true)
    List<Movie> findByTypeAndStatusOrderByPublishedAtDesc(@Param("type") MovieType type, @Param("status") boolean status);

    // Phân trang phim theo type và status
    //JPQL Query
    @Query("SELECT m FROM Movie m WHERE m.type = :type AND m.status = :status")
    //Native SQL
    // @Query(value = "SELECT * FROM movie WHERE type = :type AND status = :status", nativeQuery = true)
    Page<Movie> findByTypeAndStatus(@Param("type") MovieType type, @Param("status") boolean status, Pageable pageable);
}
