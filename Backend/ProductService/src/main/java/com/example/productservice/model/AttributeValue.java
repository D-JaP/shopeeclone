package com.example.productservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name = "attribute_value")
public class AttributeValue {
    @Id
    @Column(name = "attribute_value_id")
    private long id;

    @Column(name = "product_id")
    private long product_id;

    @Column(name = "attribute_id")
    private long attribute_id;

    @Column(name = "type")
    private DataType type;

    @Column(name = "value")
    private String value;

}
