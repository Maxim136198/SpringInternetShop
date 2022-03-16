package by.itstep.springInternetShop.service;


import by.itstep.springInternetShop.dao.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User save(User user);

    void deleteUser(User user);

    void deleteUserById(Long id);

    User updateUser(User user);

    List<User> findAll();

    User findById(Long id);

    User findByName(String name);

    User getUserName();

    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
