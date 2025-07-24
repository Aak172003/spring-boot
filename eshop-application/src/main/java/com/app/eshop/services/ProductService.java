package com.app.eshop.services;

import com.app.eshop.dto.ProductRequest;
import com.app.eshop.dto.ProductResponse;
import com.app.eshop.dto.UserResponse;
import com.app.eshop.models.Product;
import com.app.eshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Service
//@RequiredArgsConstructor
//public class ProductService {
//    private final ProductRepository productRepository;
////    private  ProductRepository productRepository;
////    public ProductService(ProductRepository productRepository) {
////        this.productRepository = productRepository;
////    }
//}


// 2 Way
@Service
@RequiredArgsConstructor
public class ProductService {

//    this is dependency injection automatically because this is a auto-inject dependency inside it by the annotation is @Spring
    private  final  ProductRepository productRepository;


    public List<ProductResponse> fetchAllProducts(){
    System.out.println("productRepository.findAll() ::::::::::::::::: "  + productRepository.findAll());
        return  productRepository.findAll().stream()
                .map(this::mapToProductResponce)
                .collect(Collectors.toList());
    }

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = new Product();
        updateProductFromRequest(product, productRequest);
        Product savedProject = productRepository.save(product);
        return mapToProductResponce(savedProject);
    }

    public Optional<ProductResponse> updateProduct(Long id , ProductRequest productRequest){
        System.out.println("productRepository.findById(id) ::::::::::::::::::::::::: " + productRepository.findById(id));
        return productRepository.findById(id).map(existingProduct -> {
            updateProductFromRequest(existingProduct, productRequest);
            Product savedProduct = productRepository.save(existingProduct);
            return mapToProductResponce(savedProduct);
        });
    }

//  Filter Before Respond
    private ProductResponse mapToProductResponce(Product savedProject) {
        ProductResponse response =  new ProductResponse();
        response.setId(savedProject.getId());
        response.setName(savedProject.getName());
        response.setActive(savedProject.getActive());
        response.setCategory(savedProject.getCategory());
        response.setDescription(savedProject.getDescription());
        response.setPrice(savedProject.getPrice());
        response.setImageUrl(savedProject.getImageUrl());
        response.setStockQuantity(savedProject.getStockQuantity());

        return  response;
    }

//  Filter Before Passing Request
    private void updateProductFromRequest(Product product , ProductRequest productRequest){
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(productRequest.getImageUrl());
        product.setStockQuantity(productRequest.getStockQuantity());

    }
}





