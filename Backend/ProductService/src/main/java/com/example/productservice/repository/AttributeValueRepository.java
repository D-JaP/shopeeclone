package com.example.productservice.repository;

import com.example.productservice.model.AttributeValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AttributeValueRepository extends PagingAndSortingRepository<AttributeValue,Long> , CrudRepository<AttributeValue, Long> {

}
