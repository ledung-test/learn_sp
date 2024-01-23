package com.example.movie.service;

import com.example.movie.entity.User;
import com.example.movie.exception.BadRequestException;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.model.enums.UserRole;
import com.example.movie.model.request.LoginRequest;
import com.example.movie.model.request.RegisterRequest;
import com.example.movie.reponsitory.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private HttpSession session;
    public void login(LoginRequest request) {
        //Tìm kiếm user theo email
        //Nếu tìm thấy thì kiểm tra password
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Email hoặc password không chính xác"));
        if (!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new BadRequestException("Email hoặc password không chính xác");
        }
        session.setAttribute("currentUser", user);
    }

    public void register(RegisterRequest request) {
        Boolean user = userRepository.existsByEmail(request.getEmail());
        if (user){
            throw new BadRequestException("Email đã tồn tại");
        }else {
            if (!Objects.equals(request.getPassword(), request.getConfPassword())){
                throw new ResourceNotFoundException("Mật khẩu nhập lại không trùng khớp");
            }else {
                User newUser = User.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .password(bCryptPasswordEncoder.encode(request.getPassword()))
                        .role(UserRole.USER)
                        .build();
                userRepository.save(newUser);
            }
        }
    }
}
