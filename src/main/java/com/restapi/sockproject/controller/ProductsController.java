package com.restapi.sockproject.controller;

import com.restapi.sockproject.model.Product;
import com.restapi.sockproject.service.ProductsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/products/")
@Api(value = "ProductsControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {

    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping(path = "allProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productsService.getAllProducts();
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ApiOperation("Gets the product with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Product.class)})
    public Optional<Product> getProduct(@PathVariable(name = "id") int id) {
        return productsService.getProduct(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody Product productToSave) {
        return productsService.saveProduct(productToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product productToUpdate, @PathVariable(name = "id") int id) {
        return productsService.updateProduct(productToUpdate, id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(name = "id") int id) {
        productsService.deleteProduct(id);
    }


}
