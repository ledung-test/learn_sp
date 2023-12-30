package com.example.mini_pr.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Product {
    Integer id;
    String name;
    String description;
    String thumbnail;
    Integer price;
    Double rating;
    Integer priceDiscount;
}
