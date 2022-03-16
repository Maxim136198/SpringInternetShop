package by.itstep.springInternetShop.controller;

import by.itstep.springInternetShop.dao.entity.Role;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

public interface RoleController {

    String getAllRoles(Model model);

    String removeRole(@PathVariable("id") Long id);

    String createRole(Model model);

    String saveRole(@ModelAttribute("newRole") Role role);

    String showUpdateForm(@PathVariable("id") long id, Model model);

    String updateRole(@Valid Role role, Model model);
}
