package com.example.service.impl;


import com.example.dao.entity.Product;
import com.example.dao.entity.Role;
import com.example.dao.repository.RoleRepository;
import com.example.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
//@Transactional
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;



    public RoleServiceImpl(RoleRepository rolesRepository) {
        this.roleRepository = rolesRepository;
    }

    @Override
    public Role save(Role role) {
        validate(role.getId() != null, "error.role.notHaveId");
        validate(roleRepository.existsByName(role.getName()),"error.role.name.notUnique");
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public void delete(Role role) {
        final Long id = role.getId();
        validate(id == null, "error.role.haveId");
        findById(id);
        roleRepository.delete(role);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        roleRepository.deleteById(id);
    }

    @Override
    public Role updateRole(Role role) {
        final Long id = role.getId();
        validate(id == null, "error.role.haveId");

        final Optional<Role> duplicateRole = roleRepository.findByName(role.getName());

        findById(id);
        final boolean isDuplicateExists = duplicateRole.isPresent() && !Objects.equals(duplicateRole.get().getId(), id);

        validate(isDuplicateExists, "error.role.name.notUnique");
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("role not found"));
    }

    @Override
    public Role findByName(String name) {
//        validate(!rolesRepository.existsByName(name),"error.user.name.notUnique" );
        return roleRepository.findByName(name).orElseThrow(()-> new RuntimeException("error.user.name.notFound"));
    }

    @Override
    public Page<Role> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.roleRepository.findAll(pageable);
    }


    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }


}

