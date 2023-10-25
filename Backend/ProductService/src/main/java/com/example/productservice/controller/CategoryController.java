package com.example.productservice.controller;

import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

@BasePathAwareController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;

//    @GetMapping(path = "category")
//    public ResponseEntity<?> getCategories(){
//        Category categories = categoryRepository.findById(15L).orElse(null);
//        assert categories != null;
//        EntityModel<Category> resources =  EntityModel.of(categories);
//        resources.add(linkTo(methodOn(CategoryController.class).getCategories()).withSelfRel());
//
//        return ResponseEntity.ok(resources);
//    }
}
