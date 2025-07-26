package com.app.eshop.controllers;

import com.app.eshop.dto.CartItemRequest;
import com.app.eshop.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor

public class CartController {

    private  final CartService cartService;

//    Spring can inject or initialise on run-time if I create constructor
//    public CartController(CartService cartService) {
//        this.cartService = cartService;
//    }



    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader("X-User-ID") String userId , @RequestBody CartItemRequest cartItemRequest
    ){
        if(!cartService.addToCart(userId , cartItemRequest)){
            return ResponseEntity.badRequest().body("Product Out of Stock or User Not Found or Product Not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
