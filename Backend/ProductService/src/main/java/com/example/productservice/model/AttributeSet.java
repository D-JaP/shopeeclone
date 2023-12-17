package com.example.productservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.rest.core.config.Projection;
import org.w3c.dom.Attr;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "attribute_set")
public class AttributeSet {
    @Id
    @Column(name = "attribute_set_id")
    private long id;

    @Column(name= "name")
    private String name;

    @ManyToMany(mappedBy = "attributeSet")
    private List<Attribute> attributes;

    @OneToMany( mappedBy = "attributeSet")
    private List<Product> products;

}
