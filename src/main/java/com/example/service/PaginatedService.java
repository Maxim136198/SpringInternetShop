package com.example.service;

import com.example.dao.entity.Product;
import org.springframework.data.domain.Page;

public interface PaginatedService {

     Page <Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
