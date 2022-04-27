package com.example.ecommerce.reprositry;

import com.example.ecommerce.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
// this class is for communicating with the database,
// it extends the Jpa repository and take the name of the entity class and the type of the primary key for that class
public interface ProductRepository extends JpaRepository<Products,Long> {
}
