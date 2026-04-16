package com.ecommerce.ecommerseproject.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import jakarta.annotation.Generated;

import java.util.Date;

import org.springframework.stereotype.Indexed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;
}
