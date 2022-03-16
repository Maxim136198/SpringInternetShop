package by.itstep.springInternetShop.service;

import by.itstep.springInternetShop.dao.entity.Product;
import org.springframework.data.domain.Page;

public interface PaginatedService {

     Page <Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
