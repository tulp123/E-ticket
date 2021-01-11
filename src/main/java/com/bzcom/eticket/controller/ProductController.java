package com.bzcom.eticket.controller;

import com.bzcom.eticket.model.Product;
import com.bzcom.eticket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/products"})
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get All Product
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    // Get All Product By Team id
    @GetMapping("/team")
    public List<Product> getAllProductsByTeamId(@RequestParam int teamId){
        return productService.findByTeamId(teamId);
    }

    // Get a Single Product
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(value = "id") Integer id) {
        return productService.findById(id);
    }

    // Create a new Product
    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return productService.save(product);
    }

    // Update a Product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(value = "id") Integer productId, @RequestBody Product productDetail) {
        productDetail.setId(productId);
        Product productUpdate = productService.save(productDetail);
        return productUpdate;
    }
}
