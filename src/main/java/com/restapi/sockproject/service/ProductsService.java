package com.restapi.sockproject.service;

import com.restapi.sockproject.model.Product;
import com.restapi.sockproject.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private Logger LOG = LoggerFactory.getLogger(ProductsService.class);

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        List<Product> list = new ArrayList<>();
        productRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    public Optional<Product> getProduct(int id) {
        LOG.info("Getting the product with given id:" + id);
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        Product productToSave;
        try {
            LOG.info("Saving product...");
            productToSave = productRepository.save(product);
            return productToSave;
        } catch (Exception e) {
            LOG.error("An error occurred during product saving:" + e.getMessage());
        }
        return new Product();
    }

    public Product updateProduct(Product productToUpdate, int id) {
        Product foundProduct = productRepository.findById(id).get();
        try {
            foundProduct.setName(productToUpdate.getName());
            foundProduct.setDescription(productToUpdate.getDescription());
            foundProduct.setType(productToUpdate.getType());
            foundProduct.setCategory(productToUpdate.getCategory());
            return productRepository.save(foundProduct);
        } catch (Exception e) {
            LOG.error("An error pccurred during update of product" + e.getMessage());
        }
        return productToUpdate;
    }

    public void deleteProduct(int id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("An error occurred during deleting of product:" + e.getMessage());
        }
    }

    public Page<Product> getPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> findByCategory(String category, Pageable pageable){
        return productRepository.findByCategory(category,pageable);
    }

}
