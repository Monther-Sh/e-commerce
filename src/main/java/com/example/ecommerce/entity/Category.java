package com.example.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // used instead of creating the setters and getters for the variables
@NoArgsConstructor // replace the no argument constructor for the class
@AllArgsConstructor // replace the all argument constructor for the class
@Entity // identify the class as an entity so the program knows this class is for creating tables in the database
@Table // allow you to edit the name of the variables before making the table
public class Category {
    @Id // to identify the primary key
    @GeneratedValue( strategy = GenerationType.IDENTITY) // to make the primary key auto-incremented

    Long id;

    @Column(nullable = false)// to specify that this isa column
    String name;

    @Column
    String description;

}
