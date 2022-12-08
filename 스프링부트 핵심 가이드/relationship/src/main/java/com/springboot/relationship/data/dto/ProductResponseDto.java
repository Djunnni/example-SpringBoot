package com.springboot.relationship.data.dto;

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
