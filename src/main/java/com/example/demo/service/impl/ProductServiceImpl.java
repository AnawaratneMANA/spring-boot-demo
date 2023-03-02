package com.example.demo.service.impl;

import com.example.demo.model.ProductCategory;
import com.example.demo.model.ProductsModel;
import com.example.demo.repository.ProductCategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.response.ProductCategoryResponse;
import com.example.demo.response.ProductResponse;
import com.example.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.Constants.HttpCodesMessages.*;

@Service
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
        return productCategory;
    }

    @Override
    public ProductsModel saveProduct(ProductsModel productsModel) {
        productRepository.save(productsModel);
        return productsModel;
    }

    @Override
    public ProductCategoryResponse getAllProductCategories() {
        ProductCategoryResponse productCategoryResponse = new ProductCategoryResponse();
        try {
            productCategoryResponse.setListOfProductCategories(productCategoryRepository.findAll());
            productCategoryResponse.setStatusCode(HTTP_200_CODE);
            productCategoryResponse.setMessage("Success!");
            return productCategoryResponse;
        } catch (Exception e){
            logger.error("Something wrong with getting all Product Categories");
            productCategoryResponse.setStatusCode(HTTP_500_CODE);
            productCategoryResponse.setMessage("Internal Server Error!");
            return null;
        }
    }

    @Override
    public ProductResponse getProductsFromCategory(Long id) {
        List<ProductsModel> productsFromCategory = productRepository.findByProductCategoryId(id);
        System.out.println(productsFromCategory);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setStatusCode(HTTP_200_CODE);
        productResponse.setListOfProducts(productsFromCategory);
        return productResponse;
    }


}
