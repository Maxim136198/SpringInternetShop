package com.example.service.impl;


import com.example.dao.entity.User;
import com.example.dao.repository.UserRepository;
import com.example.service.RoleService;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static long USER_COUNT;

    private final UserRepository userRepository;

    private RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User save(User user) {
        validate(user.getId() != null, "error.user.notHaveId");
        validate(userRepository.existsByName(user.getName()), "error.user.name.notUnique");

//        user.setId(++USER_COUNT);

        return userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(User user) {
        final Long id = user.getId();
        validate(id == null, "error.user.haveId");
        findById(id);
        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        final Long id = user.getId();
        validate(id == null, "error.user.haveId");

        final Optional<User> duplicateUser = userRepository.findByName(user.getName());
        findById(id);

        final boolean isDuplicateExists = duplicateUser.isPresent() && !Objects.equals(duplicateUser.get().getId(), id);
        validate(isDuplicateExists, "error.user.name.notFound");
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {

        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }


    @Override
    public User findByName(String name) {
//        validate(!userRepository.existsByName(name),"error.user.name.notUnique" );
        return userRepository.findByName(name).orElseThrow(() -> new RuntimeException("error.user.name.notFound"));
    }



    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
