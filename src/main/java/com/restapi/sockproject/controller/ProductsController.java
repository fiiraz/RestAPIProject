package com.restapi.sockproject.controller;

import com.restapi.sockproject.model.Product;
import com.restapi.sockproject.service.ProductsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    /*
        localhost:8080/api/products/listPageable?page=1&size=2&sort=category,DESC ile category'e gore sort islemi yapilip ardindan
        2'serli page'lere bolunup 1.(2.) page verileri Page seklinde geri donduruluyor.(List'de donulebilir)
     */
    @RequestMapping(value = "listPageable", method = RequestMethod.GET)
    public Page<Product> productsPageable(Pageable pageable) {

        return productsService.getPageable(pageable);
    }

    /*
        localhost:8080/api/products/listPageable2?category=Special&page=0&size=1 ile category'si Special olan veriler 1'erli page'lere bolunup
        0.(1.) page verileri List seklinde geri donduruluyor.
     */
    @RequestMapping(value = "listPageable2", method = RequestMethod.GET)
    public List<Product> productsPageable2(
            @RequestParam("category") String category,
            Pageable pageable) {

            return productsService.findByCategory(category, pageable);
    }


}
