package com.restapi.sockproject;

import com.restapi.sockproject.model.Product;
import com.restapi.sockproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    }
}
