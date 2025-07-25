package com.app.eshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
//    private Long id;
    private String name;
    private String description ;
    private BigDecimal price;
    private Integer stockQuantity;
    private String category;
    private boolean active;
    private String imageUrl;
}
