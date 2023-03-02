package com.example.demo.response;

import com.example.demo.model.ProductCategory;
import com.example.demo.model.ProductsModel;
import lombok.Data;

import java.util.List;
@Data
public class ProductCategoryResponse extends GeneralResponse {
    private List<ProductCategory> listOfProductCategories;
}
