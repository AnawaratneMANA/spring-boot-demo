package com.example.demo.service;

import com.example.demo.model.ProductCategory;
import com.example.demo.model.ProductsModel;
import com.example.demo.response.ProductResponse;

import java.util.List;

public interface ProductService {
    // Insert new Product Category
    public ProductCategory saveProductCategory(ProductCategory productCategory);

    // Insert new Product
    public ProductsModel saveProduct(ProductsModel productsModel);

    //TODO:Retrieve all products
    public ProductResponse getProductsFromCategory(Long id);
}
