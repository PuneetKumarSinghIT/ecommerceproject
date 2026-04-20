package com.ecommerce.ecommerseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerseproject.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // create a query like this "select * form product where description = ?"
    Product findByDescription(String description);
    
    // Create a query like this "select * from product where title = ?"
    Product findByTitle(String title);

    // This will insert the product record in table.
    // save() method is inherited from JpaRepository
}
