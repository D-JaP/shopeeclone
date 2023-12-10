package com.example.productservice.controller;

import com.example.productservice.dto.AttributeRequest;
import com.example.productservice.dto.ProductFormUpload;
import com.example.productservice.model.AttributeValue;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@BasePathAwareController
@RequiredArgsConstructor
@Log4j2
public class ProductController {
    private final ProductService productService;

    @PostMapping(path = "product")
    @PreAuthorize("@authorizationService.hasAnyRole(#request, 'USER', 'ROLE_USER') ")
    public ResponseEntity<String> addProduct(HttpServletRequest request,
                                             @RequestParam(name = "name") String name,
                                             @RequestParam(name = "price") float price,
                                             @RequestParam(name = "category_id") Long category_id,
                                             @RequestParam(name = "description") String description,
                                             @RequestParam(name = "attribute_set_id", required = false) Long attribute_set_id,
                                             @RequestParam(name = "seller", required = false) Long seller_id,
                                             @RequestParam(name = "image") List<MultipartFile> files,
                                             @RequestPart (name = "attribute", required = false) List<AttributeValue> attributes
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
                .attributes(attributes)
                .build();

        String response  = productService.addProductFromFormUpload(productFormUpload);

        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "product/{id}")
    @PreAuthorize("@authorizationService.isProductOwner(#request, #id) && @authorizationService.hasAnyRole(#request, 'USER', 'ROLE_USER') ")
    public ResponseEntity<String> addProduct(HttpServletRequest request,
                                             @PathVariable() long id, @RequestParam(name = "name", required = false) String name,
                                             @RequestParam(name = "price" , required = false) float price,
                                             @RequestParam(name = "category_id" , required = false) Long category_id,
                                             @RequestParam(name = "description" , required = false) String description,
                                             @RequestParam(name = "attribute_set_id", required = true) Long attribute_set_id,
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
    @PreAuthorize("@authorizationService.isProductOwner(#request, #id) && @authorizationService.hasAnyRole(#request, 'USER', 'ROLE_USER') ")
    public ResponseEntity<String> deleteProductImage(HttpServletRequest request, @PathVariable Long id){
        String response = productService.deleteProductImage(id);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("@authorizationService.isProductOwner(#request, #id) && @authorizationService.hasAnyRole(#request, 'USER', 'ROLE_USER') ")
    @DeleteMapping(path = "product/image/")
    public ResponseEntity<String> deleteProductImageList(HttpServletRequest request, @RequestBody List<Long> idList){
        String response = productService.deleteProductImage(idList);

        return ResponseEntity.ok(response);
    }

    @GetMapping(path  = "product")
    public ResponseEntity<?> getProduct(){
        return ResponseEntity.ok().build();
    }
}
