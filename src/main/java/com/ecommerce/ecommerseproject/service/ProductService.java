package com.ecommerce.ecommerseproject.service;

import com.ecommerce.ecommerseproject.exceptions.ProductNotFoundException;
import com.ecommerce.ecommerseproject.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(long productId) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product createProduct(long id, String title, String description, double price, String category, String imageUrl);
}
