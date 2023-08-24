package com.dailycodebuffer.ProductService.model;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private Long price;
    private Long quantity;
}
