package com.example.productservice.repository;

import com.example.productservice.model.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "category", path = "category")
public interface CategoryRepository extends PagingAndSortingRepository<Category,Long>, CrudRepository<Category, Long> {
    Optional<Category> findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM category WHERE parent_id = :parent_id", nativeQuery = true)
    List<Category> getCategoriesByParentId(@Param("parent_id") int id, Pageable pageable);

    List<Category> findAllByLevel(@PathVariable("level") int level, Pageable pageable);
    @Override
    @PreAuthorize("@authorizationService.hasAnyRole(#request,'ADMIN','ROLE_ADMIN')")
    <S extends Category> S save(S entity);

    @Override
    @PreAuthorize("@authorizationService.hasAnyRole(#request,'ADMIN','ROLE_ADMIN')")
    <S extends Category> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @PreAuthorize("@authorizationService.hasAnyRole(#request,'ADMIN','ROLE_ADMIN')")
    void deleteById(Long aLong);

    @Override
    @PreAuthorize("@authorizationService.hasAnyRole(#request,'ADMIN','ROLE_ADMIN')")
    void delete(Category entity);

    @Override
    @PreAuthorize("@authorizationService.hasAnyRole(#request,'ADMIN','ROLE_ADMIN')")
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    @PreAuthorize("@authorizationService.hasAnyRole(#request,'ADMIN','ROLE_ADMIN')")
    void deleteAll(Iterable<? extends Category> entities);

    @Override
    @PreAuthorize("@authorizationService.hasAnyRole(#request,'ADMIN','ROLE_ADMIN')")
    void deleteAll();

}
