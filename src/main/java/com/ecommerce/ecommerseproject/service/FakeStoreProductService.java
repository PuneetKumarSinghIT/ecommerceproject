package com.ecommerce.ecommerseproject.service;

import com.ecommerce.ecommerseproject.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

//    Inside this Fakestore is third party implementation.
    @Override
    public Product getSingleProduct(long productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
