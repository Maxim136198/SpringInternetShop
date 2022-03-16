package by.itstep.springInternetShop.dao.repository;


import by.itstep.springInternetShop.dao.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByName(String name);

    Optional<User>  findByName(String name);

    List<User> findAll (Sort sort);


}