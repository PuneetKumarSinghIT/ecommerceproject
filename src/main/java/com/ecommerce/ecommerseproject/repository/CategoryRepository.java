package com.ecommerce.ecommerseproject.repository;

import org.springframework.lang.NonNull;
import com.ecommerce.ecommerseproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // create a query like this "select * form category where title = ?"
    Category findByTitle(String title);

    // This will insert the category record in table.
    Category save(@NonNull Category category);
  
}
