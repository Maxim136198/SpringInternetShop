package by.itstep.springInternetShop.service.impl;


import by.itstep.springInternetShop.dao.repository.ProductRepository;
import by.itstep.springInternetShop.service.PaginatedService;
import by.itstep.springInternetShop.dao.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginatedServiceImpl implements PaginatedService {

    ProductRepository productRepository;

    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.productRepository.findAll(pageable);
    }
}
