package com.example.service;


import com.example.dao.entity.Product;
import com.example.dao.entity.Role;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    void delete(Role role);

    void deleteById(Long id);

    Role updateRole(Role role);

    List<Role> findAll();

    Role findById(Long id);

    Role findByName(String name);

    Page<Role> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

