package com.example.service;



import com.example.dao.entity.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    void delete(Product product);

    void deleteById(Long id);

    Product updateProduct(Product product);

    List<Product> findAll();

    Product findById(Long id);

    Product findByName(String name);

//    List<Product> findAllByCategory();
}
