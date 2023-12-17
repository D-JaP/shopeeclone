package com.example.productservice.repository;

import com.example.productservice.model.AttributeSet;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.model.ProductImage;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "ProductProjection", types = {Product.class})
public interface ProductProjection {
    AttributeSet getAttributeSet();
    String getName();
    String getDescription();
    String getPrice();
    List<ProductImage> getImageUrls();
    Long getSeller();
    Category getCategory_id();
}
