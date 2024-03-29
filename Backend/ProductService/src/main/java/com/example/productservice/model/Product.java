package com.example.productservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "product")
public class Product {
    @Id
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", initialValue = 4, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_id_seq")
    @Column(name = "product_id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "price")
    private float price;

    @Column(name = "description")
    private String description;

    @Column(name = "seller")
    private Long seller;

    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL)
    private List<AttributeValue> attribute_value;

    @ManyToOne()
    @JoinColumn(name = "attribute_set_id")
    private AttributeSet attributeSet;

    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> imageUrls;

    @Column(name = "quantity")
    private Integer quantity;

    public Long getCategoryId() {
        return category.getId();
    }
}
