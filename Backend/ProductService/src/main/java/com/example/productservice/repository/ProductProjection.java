package com.example.productservice.repository;

import com.example.productservice.model.*;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "ProductProjection", types = {Product.class})
public interface ProductProjection {
    Long getId();
    AttributeSet getAttributeSet();
    String getName();
    String getDescription();
    String getPrice();
    List<ProductImage> getImageUrls();
    Long getSeller();
    Category getCategory();
    List<AttributeValue> getAttribute_value();
    Integer getQuantity();
    Long getCategoryId();
}
