package com.app.eshop.repositories;

import com.app.eshop.models.Product;
import com.app.eshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ProductRepository extends JpaRepository<Product, Long>{
}


