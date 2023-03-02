package com.example.demo.service;

import com.example.demo.model.ProductCategory;
import com.example.demo.model.ProductsModel;
import com.example.demo.response.ProductCategoryResponse;
import com.example.demo.response.ProductResponse;

import java.util.List;

public interface ProductService {
    // Insert new Product Category
    public ProductCategory saveProductCategory(ProductCategory productCategory);

    // Insert new Product
    public ProductsModel saveProduct(ProductsModel productsModel);

    // Retrieve all product category
    public ProductCategoryResponse getAllProductCategories();
    public ProductResponse getProductsFromCategory(Long id);
}
