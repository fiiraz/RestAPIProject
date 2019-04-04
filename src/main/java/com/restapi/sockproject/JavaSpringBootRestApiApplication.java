package com.restapi.sockproject;

import com.restapi.sockproject.model.Product;
import com.restapi.sockproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JavaSpringBootRestApiApplication implements CommandLineRunner {

    private ProductRepository productRepository;

    @Autowired
    public void productRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringBootRestApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Product testProduct = new Product();
        testProduct.setName("Simple Test Product");
        testProduct.setDescription("This is a Test Product API test 123");
        testProduct.setType("Custom");
        testProduct.setCategory("Special");
        productRepository.save(testProduct);

        Product testProduct2 = new Product();
        testProduct2.setName("Simple Test Product2");
        testProduct2.setDescription("This is a Test Product API test 1232");
        testProduct2.setType("Custom2");
        testProduct2.setCategory("Special2");
        productRepository.save(testProduct2);

        Product testProduct3 = new Product();
        testProduct3.setName("Simple Test Product3");
        testProduct3.setDescription("This is a Test Product API test 1233");
        testProduct3.setType("Custom3");
        testProduct3.setCategory("Special");
        productRepository.save(testProduct3);

        Product testProduct4 = new Product();
        testProduct4.setName("Simple Test Product4");
        testProduct4.setDescription("This is a Test Product API test 1234");
        testProduct4.setType("Custom4");
        testProduct4.setCategory("Special4");
        productRepository.save(testProduct4);

    }
}
