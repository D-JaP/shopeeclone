package com.example.productservice.repository;

import com.example.productservice.model.AttributeSet;
import com.example.productservice.model.Product;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "attributeSetProjection", types = {AttributeSet.class})
public interface AttributeSetProjection {
    String getName();
    List<Product> getProducts();
}
