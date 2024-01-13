package com.example.movie.reponsitory;

import com.example.movie.entity.User;
import com.example.movie.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public List<User> findByRole(UserRole role);
}
