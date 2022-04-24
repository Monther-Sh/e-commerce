package com.example.ecommerce.reprositry;

import com.example.ecommerce.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products,Long> {
}
