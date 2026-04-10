package com.ecommerce.ecommerseproject.models;

import lombok.Getter;
import lombok.Setter;

// @Getter and @Setter will create the getter and setters for our class attributes
// as they are private to this class.
@Getter
@Setter
public class Product {
    private long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private Category category;

    public Product() {
    }

    public Product(long id, String title, String description, double price, String imageUrl, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
    }
}
