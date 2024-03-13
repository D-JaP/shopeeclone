package com.example.productservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "attribute_value")
public class AttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "attribute_id_seq")
    @Column(name = "attribute_value_id")
    private long id;

    @Column(name = "product_id")
    private long product_id;

    @JoinColumn(name = "attribute_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Attribute attribute;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DataType type;

    @Column(name = "value")
    private String value;




}
