package com.ecommerce.ecommerseproject.service;

import com.ecommerce.ecommerseproject.dto.FakeStoreProductDto;
import com.ecommerce.ecommerseproject.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

//    Inside this Fakestore is third party implementation.
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(long productId) {

        System.out.println("We are running the getSingleProduct service in fakestore");
        FakeStoreProductDto fakestoreproductdto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" +
                        productId, FakeStoreProductDto.class );
        assert fakestoreproductdto != null;
        return fakestoreproductdto.getProduct();
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
