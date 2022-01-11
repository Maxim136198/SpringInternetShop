package com.example.controller.impl;



import com.example.controller.RoleController;
import com.example.dao.entity.Role;
import com.example.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/role")
public class RoleControllerImpl implements RoleController {

private RoleService roleService;

    public RoleControllerImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/allRoles")
    public String getAllRoles(Model model) {
        model.addAttribute("allRoles", roleService.findAll());
        return "role/allRoles";
    }

    @PostMapping("/{id}")
    public String removeRole(@PathVariable("id") Long id) {
        roleService.deleteById(id);
        return "redirect:/role/allRoles";
    }

    @GetMapping("/newRole")
    public String createRole(Model model) {
        model.addAttribute("newRole", new Role());
        return "role/newRole";
    }

    @PostMapping()
    public String saveRole(@ModelAttribute("newRole") Role role) {
        roleService.save(role);
        return "redirect:/role/allRoles";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "role/update-role";
    }

    @PostMapping("/update")
    public String updateUser(@Valid Role role, Model model) {
        roleService.updateRole(role);
//        model.addAttribute("user", userService.findAll());
        return "redirect:/role/allRoles";
    }


}
