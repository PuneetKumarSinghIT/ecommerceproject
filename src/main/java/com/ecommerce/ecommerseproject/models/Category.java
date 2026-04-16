package com.ecommerce.ecommerseproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category extends BaseModel {
    private String title;

    @Override
    public String toString() {
        return "Category{ title='" + title + '\'' +
                '}';
    }
}
