package com.app.eshop.controllers;

import com.app.eshop.dto.ProductRequest;
import com.app.eshop.dto.ProductResponse;
import com.app.eshop.dto.UserResponse;
import com.app.eshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

//    RequiredArgsConstructor will only work with final keyword
    private final ProductService productService;

//    So , to auto allow auto-inject dependency injection
//    1. 1st way is by adding manually constructor
//    2. Add @AutoWired
//    3. Add RequireArgsConstructor  which we take from lombok
//    If I don't want to add manually , we can use Auto-Wired annotation or @RequiredArgsConstructor'
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct (@RequestBody ProductRequest productRequest){
        return new ResponseEntity<ProductResponse>(productService.createProduct(productRequest),
        HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> createProduct (
            @PathVariable Long id,
            @RequestBody ProductRequest productRequest){
        return productService.updateProduct(id,productRequest).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    //    @RequestMapping(value = "" , method = RequestMethod.GET)
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        System.out.println(productService.fetchAllProducts() + " get all user -------------");
        return new ResponseEntity<>(productService.fetchAllProducts(), HttpStatus.OK);
        // return  ResponseEntity.ok(userService.fetchAllUsers());
        // return  userService.fetchAllUsers();

    }
}
