package com.example.productservice.dto;

import com.example.productservice.model.AttributeValue;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@SuperBuilder
public class ProductFormUpload {
    private String name;
    private Float price;
    private Long category_id;
    private String description;
    private Long attribute_set_id;
    private List<MultipartFile> imageFile;
    private Long seller_id;
    private List<AttributeValue> attributes;
}
