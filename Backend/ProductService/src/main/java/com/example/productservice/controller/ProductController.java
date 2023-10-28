package com.example.productservice.controller;

import com.example.productservice.dto.ProductFormChangeDetail;
import com.example.productservice.dto.ProductFormUpload;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@BasePathAwareController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(path = "product")
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

    @PatchMapping(path = "product/{id}")
    public ResponseEntity<String> addProduct(@PathVariable() long id, @RequestParam(name = "name", required = false) String name,
                                             @RequestParam(name = "price" , required = false) float price,
                                             @RequestParam(name = "category_id" , required = false) Long category_id,
                                             @RequestParam(name = "description" , required = false) String description,
                                             @RequestParam(name = "attribute_set_id", required = false) Long attribute_set_id,
                                             @RequestParam(name = "seller", required = false) Long seller_id,
                                             @RequestParam(name = "image", required = false) List<MultipartFile> files
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

        String response  = productService.modifyProduct(id, productFormUpload);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "product/image/{id}")
    public ResponseEntity<String> deleteProductImage(@PathVariable Long id){
        String response = productService.deleteProductImage(id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "product/image/")
    public ResponseEntity<String> deleteProductImageList(@RequestBody List<Long> idList){
        String response = productService.deleteProductImage(idList);

        return ResponseEntity.ok(response);
    }

}
