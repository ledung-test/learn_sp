package com.example.sp_security2.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(customUserDetailsService);
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws  Exception{
        //Cấu hình đường dẫn, lỗi 401 và 403
        String[] publicPath = {"/phim-le", "phim-bo", "/phim-chieu-rap", "/tin-tuc"};
        httpSecurity.authorizeHttpRequests(authorizeRequests -> {
//                    authorizeRequests.requestMatchers("/").permitAll();//Ai cũng truy cập được
//                    authorizeRequests.requestMatchers("/css/**","/js/**","/image/**").permitAll();// Các đường dẫn
//                    authorizeRequests.requestMatchers(publicPath).permitAll();//Các đường dẫn public
//                    authorizeRequests.requestMatchers("/profile").hasRole("USER");//Có role USER mới truy cập được
//                    authorizeRequests.requestMatchers("/admin").hasRole("ADMIN");//Có role ADMIN mới truy cập được
//                    authorizeRequests.requestMatchers("/author").hasAnyRole("ADMIN", "AUTHOR");//Có role ADMIN hoặc AUTHOR mới truy cập được
//                    authorizeRequests.requestMatchers("/user", "/movie").hasAuthority("ROLE_ADMIN");//Có authority ROLE_ADMIN mới truy cập được
//                    authorizeRequests.requestMatchers("/khach-hang", "/don-hang").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");//Có
//                    //authority là ROLE_USER hoặc ROLE_ADMIN mới truy cập được
//                    authorizeRequests.requestMatchers("GET", "/aa/**", "/bb/**").hasRole("ADMIN");//Có role admin mới truy cập được
//                    authorizeRequests.anyRequest().authenticated();//Tất cả các request khác đều cần xác thực
            authorizeRequests.anyRequest().permitAll();
        }
        );
        //Cấu hình form login
        httpSecurity.formLogin(formLogin -> {
            //formLogin.defaultSuccessUrl("/", true);//Login thành công chuyển hướng về trang chủ
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/handle-login");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("pass");
            formLogin.permitAll();//Tất cả đều được phép truy cập
        });
        //Cấu hình logout
        httpSecurity.logout(logout -> {
            logout.logoutSuccessUrl("/");//Logut thành công thì chuyển hướng về trang chủ
            logout.deleteCookies("JSESSIONID");//Xóa cookie JSESSIONID
            logout.invalidateHttpSession(true);//Hủy session
            logout.clearAuthentication(true);//Xóa thông tin xác thực
            logout.permitAll();//Tất cả đều được truy cập
        });
        //Cấu hình xác thực
        httpSecurity.authenticationProvider(authenticationProvider());
        return httpSecurity.build();
    }
}
