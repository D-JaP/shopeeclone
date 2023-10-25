package com.example.productservice.controller;

import com.example.productservice.dto.ProductFormUpload;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@BasePathAwareController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PutMapping(path = "product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addProduct(@RequestParam(name = "name") String name,
                                             @RequestParam(name = "price") float price,
                                             @RequestParam(name = "category_id") Long category_id,
                                             @RequestParam(name = "description") String description,
                                             @RequestParam(name = "attribute_set_id", required = false) Long attribute_set_id,
                                             @RequestParam(name = "seller", required = false) Long seller_id,
                                             @RequestParam(name = "image") List<MultipartFile> files
                                             )

    {
        ProductFormUpload productFormUpload = ProductFormUpload.builder()
                .name(name)
                .price(price)
                .category_id(category_id)
                .description(description)
                .seller_id(seller_id)
                .attribute_set_id(attribute_set_id)
                .imageFile(files)
                .build();

        String response  = productService.addProductFromFormUpload(productFormUpload);

        return ResponseEntity.ok(response);
    }
}
