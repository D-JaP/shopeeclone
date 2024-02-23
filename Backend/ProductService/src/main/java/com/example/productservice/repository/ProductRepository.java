package com.example.productservice.repository;

import com.example.productservice.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "product", path = "product", excerptProjection = ProductProjection.class)
public interface ProductRepository extends JpaRepository<Product, Long> {

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
