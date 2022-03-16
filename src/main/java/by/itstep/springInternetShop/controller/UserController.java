package by.itstep.springInternetShop.controller;

import by.itstep.springInternetShop.dao.entity.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface UserController {

    String getAllUser(Model model);

    String removeUser(@PathVariable("id") Long id);

    String createUser(Model model);

    String saveUser(@ModelAttribute("newUser") User user);

    String showUpdateForm(@PathVariable("id") long id, Model model);

    String updateUser(@Valid User user, Model model);

    String userLogin(Model model);

    String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                         @RequestParam("sortField") String sortField,
                         @RequestParam("sortDir") String sortDir,
                         Model model);

}
