package com.example.productservice.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "attribute")
public class Attribute {

    @Id
    @Column(name = "attribute_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "datatype")
    private DataType dataType;

    @Column(name = "is_require")
    private boolean is_require;

    @Column(name = "default_value")
    private String default_value;

    @ManyToMany()
    @JoinTable(
            name = "attribute_attribute_set",
            joinColumns = @JoinColumn(name = "attribute_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_set_id")
    )
    private List<AttributeSet> attributeSet;


}
