package com.example.productservice.repository;

import com.example.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

    @Override
//    @PreAuthorize("@authorizationService.isProductOwner(@httpServletRequest,#entity.getId()) " +
//            "&& @authorizationService.hasRole(@httpServletRequest, 'ROLE_USER')")
    <S extends Product> S save(S entity);

    @Override
//    @PreAuthorize("@authorizationService.isProductOwner(@httpServletRequest,#entity.getId()) " +
//            "&& @authorizationService.hasRole(@httpServletRequest, 'ROLE_USER')")
    <S extends Product> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @PreAuthorize("@authorizationService.isProductOwner(@httpServletRequest,#entity.getId()) " +
            "&& @authorizationService.hasRole(@httpServletRequest, 'ROLE_USER')")
    void deleteById(Long aLong);

    @Override
    @PreAuthorize("@authorizationService.isProductOwner(@httpServletRequest,#entity.getId()) " +
            "&& @authorizationService.hasRole(@httpServletRequest, 'ROLE_USER')")
    void delete(Product entity);

    @Override
    @PreAuthorize("@authorizationService.isProductOwner(@httpServletRequest,#entity.getId()) " +
            "&& @authorizationService.hasRole(@httpServletRequest, 'ROLE_USER')")
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    @PreAuthorize("@authorizationService.isProductOwner(@httpServletRequest,#entity.getId()) " +
            "&& @authorizationService.hasRole(@httpServletRequest, 'ROLE_USER')")
    void deleteAll(Iterable<? extends Product> entities);

    @Override
    @PreAuthorize("@authorizationService.isProductOwner(@httpServletRequest,#entity.getId()) " +
            "&& @authorizationService.hasRole(@httpServletRequest, 'ROLE_USER')")
    void deleteAll();
}
