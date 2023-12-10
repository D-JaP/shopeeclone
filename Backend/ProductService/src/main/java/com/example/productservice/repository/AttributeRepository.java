package com.example.productservice.repository;

import com.example.productservice.model.Attribute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "attribute" , path = "attribute")
public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Long>, CrudRepository<Attribute, Long> {

}
