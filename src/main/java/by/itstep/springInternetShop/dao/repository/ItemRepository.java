package by.itstep.springInternetShop.dao.repository;

import by.itstep.springInternetShop.dao.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


}
