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
    @SequenceGenerator(name = "product_image_id_seq", sequenceName = "product_image_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_image_id_seq")
    @Column(name = "image_id")
    private long id;

    @ManyToOne()
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    private Product product;

    @Column(name = "img_order")
    private Integer order;

    @Column(name = "image_url")
    private String imageUrl;

}
