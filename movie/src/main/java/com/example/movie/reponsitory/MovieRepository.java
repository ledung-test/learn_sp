package com.example.movie.reponsitory;

import com.example.movie.entity.Movie;
import com.example.movie.model.enums.MovieType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Movie: đối tượng thao tác
//Integer: Kiểu dũ liệu của khóa chính
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    //Tìm kiếm phim theo tiêu đề
    List<Movie> findByTitle(String title);

    //Tìm kiếm phim theo tiêu đề chứa từ khóa
    List<Movie> findByTitleContaining(String title);

    //Kiểm tra phim có tồn tại hay không theo tiêu đề
    Boolean existsByTitle(String title);

    //Đếm số lượng bản ghi
    long countByTitle(String title);

    //Tìm kiếm theo trang thái và thể loại
    List<Movie> findByStatusAndType(Boolean status, MovieType type);

    //Sắp xếp các bộ phim theo view giảm dần
    List<Movie> findAllByOrderByViewDesc();

    //Tìm kiếm phim theo trạng thái và sắp xếp theo tiêu chí nào đó
    List<Movie> findByStatus(Boolean status, Sort sort);

    //Tìm kiếm phim lẻ theo status, theo type, publishAT
    List<Movie> findByStatusAndType(Boolean status, MovieType type, Sort sort);

    Movie findByStatusAndIdAndSlug(Boolean status, Integer id, String slug);
}
