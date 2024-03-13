package com.example.productservice.repository;

import com.example.productservice.model.Attribute;
import com.example.productservice.model.AttributeValue;
import com.example.productservice.model.DataType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "AttributeValueProjection", types = {AttributeValue.class})
public interface AttributeValueProjection {
    Long getId();
    String getValue();
    Attribute getAttribute();
    Long getProduct_id();
    String getType();
    interface Attribute {
//        Long getId();
        String getName();
//        DataType getDataType();
    }
}
