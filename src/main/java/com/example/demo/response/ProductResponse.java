package com.example.demo.response;

import com.example.demo.model.ProductsModel;
import lombok.Data;

import java.util.List;
@Data
public class ProductResponse extends GeneralResponse{
    private List<ProductsModel> listOfProducts;
}
