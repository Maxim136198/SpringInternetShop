package by.itstep.springInternetShop.dao.repository;


import by.itstep.springInternetShop.dao.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    Optional<Product> findByName(String name);

    Page<Product> findAll(Pageable pageable);

//    List<Product> findAllByCategory(Product product);
}
