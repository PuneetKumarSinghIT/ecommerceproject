package com.ecommerce.ecommerseproject.service;

import com.ecommerce.ecommerseproject.exceptions.ProductNotFoundException;
import com.ecommerce.ecommerseproject.models.Product;
import com.ecommerce.ecommerseproject.models.Category;
import com.ecommerce.ecommerseproject.repository.CategoryRepository;
import com.ecommerce.ecommerseproject.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service("selfProductService")
class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository)
    {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(long productId) throws ProductNotFoundException
    {
        Optional<Product> p = productRepository.findById(productId);

        if ( p.isPresent())
        {
            return p.get();
        }

        throw new ProductNotFoundException("Not able to find the Product with given id in our database");
    }

    public List<Product> getAllProducts()
    {
        return List.of();
    }

    @Override
    public Product createProduct(long id, String title, String description, double price, String category, String imageUrl)
    {
        Product p = new Product();
        Optional<Category> currentCat = Optional.ofNullable(categoryRepository.findByTitle(category));
        if ( currentCat.isEmpty() )
        {
            // This category is not present in database and we need to create 
            // this new category so that user can put the object in this category.
            Category newCat = new Category();
            newCat.setTitle(category);
            Category newRow = categoryRepository.save(newCat);
            p.setCategory(newRow);
        }
        else
        {
            Category currentCategory = currentCat.get();
            p.setCategory(currentCategory);
        }
        p.setTitle(title);
        p.setDescription(description);
        p.setPrice(price);
        Product saveProduct = productRepository.save(p);
        return saveProduct;
    }
  
}
