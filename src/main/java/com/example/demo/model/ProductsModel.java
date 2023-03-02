package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ProductsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "productName")
    private String productName;

    @Column(name = "price")
    private double price;
}
