package com.example.productservice.repository;

import com.example.productservice.model.AttributeSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "attribute_set", path = "attribute_set")
public interface AttributeSetRepository extends PagingAndSortingRepository<AttributeSet, Long>, CrudRepository<AttributeSet, Long> {
    @Override
    void deleteById(Long aLong);

    @Override
    void delete(AttributeSet entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends AttributeSet> entities);

    @Override
    void deleteAll();
}
