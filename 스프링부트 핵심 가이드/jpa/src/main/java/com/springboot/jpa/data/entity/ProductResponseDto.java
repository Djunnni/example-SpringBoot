package com.springboot.jpa.data.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductResponseDto {
    private Long number;
    private String name;
    private int price;
    private int stock;

}
