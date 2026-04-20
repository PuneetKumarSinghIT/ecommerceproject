package com.ecommerce.ecommerseproject.controller;

import com.ecommerce.ecommerseproject.dto.ErrorDto;
import com.ecommerce.ecommerseproject.exceptions.ProductNotFoundException;
import com.ecommerce.ecommerseproject.models.Product;
import com.ecommerce.ecommerseproject.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
//    CRUD apis around product
//    For the product
//    1. to create the product
//    2. to get the product
//    3. to update the product
//    4. to delete the product

//    Need to interact with Service class.
    private ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    //    This will help in creating the product
//    @RequestMapping(value = "/product", method = RequestMethod.POST)
//    there is short annotation to do the same RequestMapping method work which is PostMapping.
    @PostMapping(value = "/product")
    public Product createProduct(@RequestBody Product product) throws ProductNotFoundException
    {
//        System.out.println("imageurl = " + product.getImageUrl());
        return productService.createProduct(product.getId(), product.getTitle(), product.getDescription(),
                product.getPrice(), product.getCategory().getTitle(), product.getImageUrl());
    }

//  This will help in getting the product.
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) throws  ProductNotFoundException
    {
        System.out.println("Starting the controller to read input");
        Product p = productService.getSingleProduct(id);
        System.out.println("Ending the controller to read input");
        ResponseEntity<Product> response = new ResponseEntity<>(
                p, HttpStatus.OK
    );
        return response;
    }

//  This will help to update the product

    public void updateProduct(Product product)
    {
    }

//    This will help to delete the product.
    public void deleteProduct(long id)
    {
    }

//    Defining the method to reply back with crisp and clear error message over UI
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e)
    {
        ErrorDto error = new ErrorDto();
        error.setMessage(e.getMessage());

        ResponseEntity<ErrorDto> response = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        return response;
    }
}
