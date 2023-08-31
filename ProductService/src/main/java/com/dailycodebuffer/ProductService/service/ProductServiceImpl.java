package com.dailycodebuffer.ProductService.service;

import com.dailycodebuffer.ProductService.entity.Product;
import com.dailycodebuffer.ProductService.exception.ProductServiceCustomException;
import com.dailycodebuffer.ProductService.model.ProductRequest;
import com.dailycodebuffer.ProductService.model.ProductResponse;
import com.dailycodebuffer.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.lang.RuntimeException;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository prodRepo;
    @Override
    public long addProduct(ProductRequest prod) {
        log.info("Adding the product");


        Product main_prod = new Product();
        main_prod.setProductName(prod.getName());
        main_prod.setPrice(prod.getPrice());
        main_prod.setQuantity(prod.getQuantity());
        prodRepo.save(main_prod);

        log.info("Product Added");
        return main_prod.getProductId();
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        log.info("Fetching all the values ");

        List<Product> temp_list = prodRepo.findAll();
        List<ProductResponse> final_List = new ArrayList<>();
        for(var i:temp_list){
            ProductResponse temp_prod = new ProductResponse();
            temp_prod.setId(i.getProductId());
            temp_prod.setName(i.getProductName());
            temp_prod.setQuantity(i.getQuantity());
            temp_prod.setPrice(i.getPrice());
            final_List.add(temp_prod);
        }

        log.info("Returning all the values in the database");
        return final_List;
    }

    public ProductResponse getProductById(Long id){
        log.info("Looking for the product by id "+id);

        Product prod_temp = prodRepo.findById(id).orElseThrow(()->new ProductServiceCustomException("Product with the specified id was not found","PRODUCT_NOT_FOUND"));
        ProductResponse prod = new ProductResponse();
        prod.setId(prod_temp.getProductId());
        prod.setName(prod_temp.getProductName());
        prod.setQuantity(prod_temp.getQuantity());
        prod.setPrice(prod_temp.getPrice());

        log.info("The product with the id "+id+" was found");
        return prod;
    }

    @Override
    public void reduceQuantity(Long productId, Long quantity) {
        log.info("Reducing the quantity for Id {} by {}",productId,quantity);

        Product prod = prodRepo.findById(productId).orElseThrow(()-> new ProductServiceCustomException("Product with given Id not found","PRODUCT_NOT_FOUND"));

        if(prod.getQuantity()<quantity){
            throw new ProductServiceCustomException(
                    "Product does not have sufficient quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        prod.setQuantity(prod.getQuantity()-quantity);
        prodRepo.save(prod);
        log.info("Product Quantity reduced to {}",prod.getQuantity());
    }

}
