package com.example.sp_security2.security;

import com.example.sp_security2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private List<User> userList;
    public CustomUserDetailsService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        userList = new ArrayList<>();
        userList.add(new User(1, "admin" ,"test1@gmail.com", passwordEncoder.encode("123") , List.of("USER", "ADMIN")));
        userList.add(new User(2, "user","test2@gmail.com", passwordEncoder.encode("123"), List.of("USER")));
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userList.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}
