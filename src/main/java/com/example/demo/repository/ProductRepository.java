package com.example.demo.repository;

import com.example.demo.model.ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductsModel, Long> {

    @Query(value = "SELECT * FROM product WHERE category_id = ?1", nativeQuery = true)
    List<ProductsModel> findByCategory(Long id);

    List<ProductsModel> findByProductCategoryId(Long id);
}
