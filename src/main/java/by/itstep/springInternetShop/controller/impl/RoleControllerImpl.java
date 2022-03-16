package by.itstep.springInternetShop.controller.impl;

import by.itstep.springInternetShop.controller.RoleController;
import by.itstep.springInternetShop.dao.entity.Role;
import by.itstep.springInternetShop.service.RoleService;
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

    @Override
    @GetMapping("/allRoles")
    public String getAllRoles(Model model) {
        model.addAttribute("allRoles", roleService.findAll());
        return "role/allRoles";
    }

    @Override
    @PostMapping("/{id}")
    public String removeRole(@PathVariable("id") Long id) {
        roleService.deleteById(id);
        return "redirect:/role/allRoles";
    }

    @Override
    @GetMapping("/newRole")
    public String createRole(Model model) {
        model.addAttribute("newRole", new Role());
        return "role/newRole";
    }

    @Override
    @PostMapping()
    public String saveRole(@ModelAttribute("newRole") Role role) {
        roleService.save(role);
        return "redirect:/role/allRoles";
    }

    @Override
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        return "role/update-role";
    }

    @Override
    @PostMapping("/update")
    public String updateRole(@Valid Role role, Model model) {
        roleService.updateRole(role);
        return "redirect:/role/allRoles";
    }


}
