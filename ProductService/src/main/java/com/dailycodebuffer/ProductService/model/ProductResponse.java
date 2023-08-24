package com.dailycodebuffer.ProductService.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Long price;
    private Long quantity;
}
