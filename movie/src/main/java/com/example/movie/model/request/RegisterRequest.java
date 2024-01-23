package com.example.movie.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    String name;
    String email;
    String password;
    String confPassword;
}
