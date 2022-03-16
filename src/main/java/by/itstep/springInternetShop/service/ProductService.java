package by.itstep.springInternetShop.service;



import by.itstep.springInternetShop.dao.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    void delete(Product product);

    void deleteById(Long id);

    Product updateProduct(Product product);

    List<Product> findAll();

    Product findById(Long id);

    Product findByName(String name);

    Page <Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

//    List<Product> findAllByCategory();
}
