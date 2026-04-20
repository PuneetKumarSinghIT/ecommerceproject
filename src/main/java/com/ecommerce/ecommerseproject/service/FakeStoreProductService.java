package com.ecommerce.ecommerseproject.service;

import com.ecommerce.ecommerseproject.dto.FakeStoreProductDto;
import com.ecommerce.ecommerseproject.exceptions.ProductNotFoundException;
import com.ecommerce.ecommerseproject.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

//    Inside this Fakestore is third party implementation.
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(long productId) throws ProductNotFoundException {

        System.out.println("We are running the getSingleProduct service in fakestore");
        FakeStoreProductDto fakestoreproductdto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" +
                        productId, FakeStoreProductDto.class );
        if (fakestoreproductdto == null){
            throw new ProductNotFoundException("Product not found"+ productId);
        }
        return fakestoreproductdto.getProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(long id,  String title, String description, double price, String category, String imageUrl) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setImage(imageUrl);

        FakeStoreProductDto response = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductDto, FakeStoreProductDto.class);
        if (response == null) {
            throw new ProductNotFoundException("Failed to create product");
        }
        return response.getProduct();
    }
}
