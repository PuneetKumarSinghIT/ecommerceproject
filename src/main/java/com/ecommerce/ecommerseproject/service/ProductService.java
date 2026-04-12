package com.ecommerce.ecommerseproject.service;

import com.ecommerce.ecommerseproject.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(long productId);
    List<Product> getAllProducts();
    Product createProduct(Product product);
}
