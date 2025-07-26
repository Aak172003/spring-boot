package com.app.eshop.repositories;
import com.app.eshop.models.CartItem;
import com.app.eshop.models.Product;
import com.app.eshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    CartItem findByUserAndProduct(User user, Product product);
}
