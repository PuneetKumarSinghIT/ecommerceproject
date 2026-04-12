package com.ecommerce.ecommerseproject.controller;

import com.ecommerce.ecommerseproject.models.Product;
import com.ecommerce.ecommerseproject.service.ProductService;
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

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //    This will help in creating the product
//    @RequestMapping(value = "/product", method = RequestMethod.POST)
//    there is short annotation to do the same RequestMapping method work which is PostMapping.
    @PostMapping(value = "/product")
    public void createProduct(Product product)
    {

    }

//  This will help in getting the product.
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") long id)
    {
        productService.getSingleProduct(id);
        return null;
    }

//  This will help to update the product
    public void updateProduct(Product product)
    {
    }

//    This will help to delete the product.
    public void deleteProduct(long id)
    {
    }

}
