package com.example.productservice.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Data
public class ProductAndAttributeRequest {
    private String name;
    private Float price;
    private Long category_id;
    private String description;
    private Long seller_id;
    private Long attribute_set_id;
    private List<MultipartFile> imageFile;
    private List<AttributeRequest> attributes;
}
