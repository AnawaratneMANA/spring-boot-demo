package com.example.demo.controllers;

import com.example.demo.exception.AgentException;
import com.example.demo.model.AgentModel;
import com.example.demo.model.ProductCategory;
import com.example.demo.model.ProductsModel;
import com.example.demo.response.AgentResponse;
import com.example.demo.response.ProductCategoryResponse;
import com.example.demo.response.ProductResponse;
import com.example.demo.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.Constants.*;
import static com.example.demo.config.Constants.AgentProperties.AGENT;
import static com.example.demo.config.Constants.HttpCodesMessages.*;
import static com.example.demo.config.Constants.HttpCodesMessages.HTTP_500_MESSAGE;
import static com.example.demo.config.Constants.ProductProperties.PRODUCT;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping( value = API + VERSION + PRODUCT, produces = APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    ProductService productService;

    @ApiOperation(value = "Get All Product Categories")
    @ApiResponses( value = {
            @ApiResponse(code = HTTP_400_CODE, message = HTTP_400_MESSAGE),
            @ApiResponse(code = HTTP_200_CODE, message = HTTP_200_MESSAGE),
            @ApiResponse(code = HTTP_500_CODE, message = HTTP_500_MESSAGE),
    })
    @GetMapping("/prod-category")
    public ResponseEntity<ProductCategoryResponse> getAllCategories() {
        return status(HttpStatus.OK).body(productService.getAllProductCategories());
    }

    @ApiOperation(value = "Save Product")
    @ApiResponses( value = {
            @ApiResponse(code = HTTP_400_CODE, message = HTTP_400_MESSAGE),
            @ApiResponse(code = HTTP_200_CODE, message = HTTP_200_MESSAGE),
            @ApiResponse(code = HTTP_500_CODE, message = HTTP_500_MESSAGE),
    })
    @PostMapping("/prod")
    public ResponseEntity<ProductsModel> saveProduct(@RequestBody ProductsModel productsModel){
        System.out.println("Product Model" + productsModel.getProductCategory().getId());
        return status(HttpStatus.OK).body(productService.saveProduct(productsModel));
    }

    @ApiOperation(value = "Save Product Category")
    @ApiResponses( value = {
            @ApiResponse(code = HTTP_400_CODE, message = HTTP_400_MESSAGE),
            @ApiResponse(code = HTTP_200_CODE, message = HTTP_200_MESSAGE),
            @ApiResponse(code = HTTP_500_CODE, message = HTTP_500_MESSAGE),
    })
    @PostMapping("/prod-category")
    public ResponseEntity<ProductCategory> saveProductCategory(@RequestBody ProductCategory productCategory){
        return status(HttpStatus.OK).body(productService.saveProductCategory(productCategory));
    }

    @ApiOperation(value = "Get All Local Agents")
    @ApiResponses( value = {
            @ApiResponse(code = HTTP_400_CODE, message = HTTP_400_MESSAGE),
            @ApiResponse(code = HTTP_200_CODE, message = HTTP_200_MESSAGE),
            @ApiResponse(code = HTTP_500_CODE, message = HTTP_500_MESSAGE),
    })
    @GetMapping("/specific/get/{id}")
    public ResponseEntity<ProductResponse> getProductFromCategory(@PathVariable Long id) throws AgentException {
        return status(HttpStatus.OK).body(productService.getProductsFromCategory(id));
    }
}
