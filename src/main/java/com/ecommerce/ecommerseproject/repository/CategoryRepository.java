package com.ecommerce.ecommerseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerseproject.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // create a query like this "select * form category where title = ?"
    Category findByTitle(String title);

    // This will insert the category record in table.
    // save() method is inherited from JpaRepository
  
}
