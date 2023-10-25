package com.example.productservice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Data
@Builder
public class ProductFormUpload {
    private String name;
    private Float price;
    private Long category_id;
    private String description;
    private Long attribute_set_id;
    private List<MultipartFile> imageFile;
    private Long seller_id;
}
