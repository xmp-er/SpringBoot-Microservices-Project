package com.dailycodebuffer.ProductService.service;

import com.dailycodebuffer.ProductService.model.ProductRequest;
import com.dailycodebuffer.ProductService.model.ProductResponse;
import java.util.List;

public interface ProductService {
    long addProduct(ProductRequest prod);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    void reduceQuantity(Long productId, Long quantity);
}
