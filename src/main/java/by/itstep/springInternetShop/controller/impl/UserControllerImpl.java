package by.itstep.springInternetShop.controller.impl;

import by.itstep.springInternetShop.dao.entity.User;
import by.itstep.springInternetShop.service.PaginatedService;
import by.itstep.springInternetShop.controller.UserController;
import by.itstep.springInternetShop.security.MyUserDetailsService;
import by.itstep.springInternetShop.service.RoleService;
import by.itstep.springInternetShop.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private UserService userService;
    private RoleService roleService;
    private PaginatedService paginatedService;
    private MyUserDetailsService myUserDetailsService;

    public UserControllerImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    @GetMapping("/allUsers")
    public String getAllUser(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        findPaginated(1, "id", "asc", model);
        return "user/allUsers";
    }

    @Override
    @PostMapping("/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/user/allUsers";
    }

    @Override
    @GetMapping("/newUser")
    public String createUser(Model model) {
        model.addAttribute("newUser", new User());
        return "user/newUser";
    }

    @Override
    @PostMapping()
    public String saveUser(@ModelAttribute("newUser") User user) {
        userService.save(user);
        return "redirect:/user/allUsers";
    }

    @Override
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/update-user";
    }

    @Override
    @PostMapping("/update")
    public String updateUser(@Valid User user, Model model) {
        userService.updateUser(user);
        return "redirect:/user/allUsers";
    }

    @Override
    @GetMapping("/login")
    public String userLogin(Model model) {
        model.addAttribute("newUser", new User());
        return "user/login";
    }

    @Override
    @GetMapping("/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> listUsers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("allUsers", listUsers);
        return "user/allUsers";
    }
}
