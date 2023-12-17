package com.example.productservice.repository;

import com.example.productservice.model.AttributeSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "attribute_set", path = "attribute_set", excerptProjection = AttributeSetProjection.class)
public interface AttributeSetRepository extends JpaRepository<AttributeSet, Long> {
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

    @Override
    Optional<AttributeSet> findById(Long aLong);

}
