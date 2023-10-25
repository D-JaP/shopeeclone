package com.example.productservice.model;

import com.example.productservice.repository.ProductRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "product_image")
public class ProductImage {
    @Id
    @Column(name = "image_id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    private Product product;

    @Column(name = "image_url")
    private String imageUrl;

}
