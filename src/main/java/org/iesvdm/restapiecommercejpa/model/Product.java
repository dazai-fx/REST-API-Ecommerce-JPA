package org.iesvdm.restapiecommercejpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
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
