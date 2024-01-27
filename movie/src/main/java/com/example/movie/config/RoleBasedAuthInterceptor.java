package com.example.movie.config;

import com.example.movie.entity.User;
import com.example.movie.model.enums.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Component
public class RoleBasedAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Lấy thông tin người dùng từ session với key là currentUser
        User user = (User) request.getSession().getAttribute("currentUser");

        //Nếu currentUser không tồn tại hoặc == null thì báo lỗi 401
        if (user == null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //Lấy role của user
        String role = user.getRole().getValue();
        if (!Objects.equals(role, "ADMIN")){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }
}
