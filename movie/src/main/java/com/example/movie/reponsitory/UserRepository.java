package com.example.movie.reponsitory;

import com.example.movie.entity.User;
import com.example.movie.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public List<User> findByRole(UserRole role);

    public Optional<User> findByEmail(String email);

    public Boolean existsByEmail(String email);

}
