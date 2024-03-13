package com.example.productservice.controller;

import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

@BasePathAwareController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "category-entity-controller", description = "Category API")
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

    @PutMapping(path = "category")
    public ResponseEntity<?> putCategory(@RequestBody Category category){
        try{
            log.info("Trying to put category with id = " + category.getId());
            categoryRepository.save(category);
            log.info("Succeeded to put category with id = " + category.getId());
            return ResponseEntity.ok().build();
        }
        catch (Exception ex){
            log.error("Failed to put category.");
            log.error(ex.getMessage());
            log.error(ex.getStackTrace());
            return ResponseEntity.internalServerError().body("Error put category");
        }
    }


    @DeleteMapping(path = "category/{id}")
    @PreAuthorize("@authorizationService.hasAnyRole(http, 'ADMIN', 'ROLE_ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") long id){
//        Check if category exist
        if(!categoryRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        else {
            try{
                log.info("Trying to delete category with id = " + id);
                categoryRepository.deleteById(id);
                log.info("Succeeded to delete category with id = " + id);
                return ResponseEntity.ok().build();
            }
            catch   (Exception ex) {
                log.error("Failed to delete category.");
                log.error(ex.getMessage());
                log.error(ex.getStackTrace());
                return ResponseEntity.internalServerError().body("Error delete category");
            }
        }
    }
}
