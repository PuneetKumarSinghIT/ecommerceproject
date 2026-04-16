package com.ecommerce.ecommerseproject.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class BaseModel {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;
}
