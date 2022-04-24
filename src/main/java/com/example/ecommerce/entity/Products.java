package com.example.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Products {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Long id;

    @Column(nullable = false)
    String name;

    @Column
    String description;

    @Column(nullable = false)
    Long purchasePrice;

    @Column(nullable = false)
    Long sellingPrice;

    @Column
    Date expiryDate;

    @Column
    String category;

}
