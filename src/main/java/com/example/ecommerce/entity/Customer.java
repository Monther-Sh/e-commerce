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
public class Customer {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)

    Long id;

    @Column(nullable = false)
    String name;

    @Column
    String email;

    @Column
    String address;

    @Column
    String cardNum;

    @Column
    Date DOB;
}
