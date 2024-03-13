package com.example.productservice.repository;

import com.example.productservice.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "product", path = "product", excerptProjection = ProductProjection.class)
public interface ProductRepository extends JpaRepository<Product, Long> {
    @RestResource(path = "byCategory" , rel = "byCategory")
    @Query(value = "select * from Product where category_id = :category_id",nativeQuery = true)
    Page<Product> findByCategory(@Param("category_id")Long category_id, Pageable pageable);

    @Query(value = "select * from Product where category_id IN (select category_id from category where parent_id = :category_id)", nativeQuery = true)
    Page<Product> findALlByCategoryLevel2(@Param("category_id") Long category_id, Pageable pageable);

    @Override
    <S extends Product> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Product> S save(S entity);

    @Override
    <S extends Product> List<S> saveAll(Iterable<S> entities);

    @Override
    @PreAuthorize("@authorizationService.isProductOwner(#httpServletRequest,#entity.getId()) " +
            "&& @authorizationService.hasRole(#httpServletRequest, 'ROLE_USER')")
    void deleteById(Long aLong);

    @Override
    @PreAuthorize("@authorizationService.isProductOwner(#httpServletRequest,#entity.getId()) " +
            "&& @authorizationService.hasRole(#httpServletRequest, 'ROLE_USER')")
    void delete(Product entity);

    @Override
    @PreAuthorize("@authorizationService.isProductOwner(#httpServletRequest,#entity.getId()) " +
            "&& @authorizationService.hasRole(#httpServletRequest, 'ROLE_USER')")
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    @PreAuthorize("@authorizationService.isProductOwner(#httpServletRequest,#entity.getId()) " +
            "&& @authorizationService.hasRole(#httpServletRequest, 'ROLE_USER')")
    void deleteAll(Iterable<? extends Product> entities);

    @Override
    @PreAuthorize("@authorizationService.isProductOwner(#httpServletRequest,#entity.getId()) " +
            "&& @authorizationService.hasRole(#httpServletRequest, 'ROLE_USER')")
    void deleteAll();
}
