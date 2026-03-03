package org.iesvdm.restapiecommercejpa.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // que diferencia hay entre identity y auto?
    private Long id;
    private String name;
    private String descrip;
    private BigDecimal price;
    private String imageUrl;
    private String sku;
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
