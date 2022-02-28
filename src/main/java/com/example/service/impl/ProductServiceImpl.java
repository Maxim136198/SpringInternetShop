package com.example.service.impl;


import com.example.dao.entity.Product;
import com.example.dao.repository.ProductRepository;
import com.example.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        validate(product.getId() != null, "error.product.notHaveId");
        validate(productRepository.existsByName(product.getName()), "error.product.name.notUnique");
        return productRepository.saveAndFlush(product);
    }

    @Override
    public void delete(Product product) {
        final Long id = product.getId();
        validate(id == null, "error.product.haveId");
        findById(id);
        productRepository.delete(product);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        productRepository.deleteById(id);

    }

    @Override
    public Product updateProduct(Product product) {
        final Long id = product.getId();
        validate(id == null, "error.product.haveId");

        final Optional<Product> duplicateProduct = productRepository.findByName(product.getName());
        findById(id);

        final boolean isDuplicateExists = duplicateProduct.isPresent() && !Objects.equals(duplicateProduct.get().getId(), id);

        validate(isDuplicateExists, "error.product.name.notUnique");
        return productRepository.saveAndFlush(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name).orElseThrow(() -> new RuntimeException("error.product.name.notUnique"));
    }

    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.productRepository.findAll(pageable);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
