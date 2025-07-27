package com.app.eshop.services;

import com.app.eshop.dto.CartItemRequest;
import com.app.eshop.models.CartItem;
import com.app.eshop.models.Product;
import com.app.eshop.models.User;
import com.app.eshop.repositories.CartItemRepository;
import com.app.eshop.repositories.ProductRepository;
import com.app.eshop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private  final UserRepository userRepository;

//    Spring can inject or initialise on run-time if I create constructor ,

//    public CartService(ProductRepository productRepository, CartItemRepository cartItemRepository, UserRepository userRepository) {
//        this.productRepository = productRepository;
//        this.cartItemRepository = cartItemRepository;
//        this.userRepository = userRepository;
//    }



    public boolean addToCart(String userId, CartItemRequest cartItemRequest) {

//       Look for product is existed in our database or not
         Optional<Product> productData = productRepository.findById(cartItemRequest.getProductId());
         if(productData.isEmpty()){
             return  false;
         }
         Product product = productData.get();
         if(product.getStockQuantity() <  cartItemRequest.getQuantity()){
             return  false;
         }

         Optional<User> userData = userRepository.findById(Long.valueOf(userId));

         if(userData.isEmpty()){
             return  false;
         }
         User user = userData.get();

        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user , product);

        if(existingCartItem != null){
        //for this particular cartItem exist for user , so we need to update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity()  + cartItemRequest.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));

            cartItemRepository.save(existingCartItem);
        }else {
//            This means not exist so create a new cartItem which is associated with that particular user

            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));

            cartItemRepository.save(cartItem);
        }

        return true;
    }

    public boolean deleteItemFromCart(String userId, Long productId) {
//      Look for product is existed in our database or not
        Optional<Product> productData = productRepository.findById(productId);
        Product productThings = productData.get();

        Optional<User> userData = userRepository.findById(Long.valueOf(userId));
        User userThings = userData.get();

        System.out.println("user ---------------- " + userThings);
        System.out.println("product -------------------- " + productThings);

        if(productData.isPresent() && userData.isPresent()){
            cartItemRepository.deleteByUserAndProduct(userData.get() , productData.get());
            return true;
        }
        return false;
    }

    public List<CartItem> getCart(String userId) {
        return userRepository.findById(Long.valueOf(userId))
                .map(cartItemRepository:: findByUser)
                .orElseGet(List::of);
    }

    public void clearCart(String userId) {
        userRepository.findById(Long.valueOf(userId)).ifPresent(cartItemRepository::deleteByUser);
    }
}
