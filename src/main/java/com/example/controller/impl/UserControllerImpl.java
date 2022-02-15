package com.example.controller.impl;


import com.example.controller.UserController;
import com.example.dao.entity.Product;
import com.example.dao.entity.User;
import com.example.security.MyUserDetails;
import com.example.security.MyUserDetailsService;
import com.example.service.PaginatedService;
import com.example.service.RoleService;
import com.example.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
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

//    @GetMapping("/index")
//    public String index(Model model) {
//        model.addAttribute("user", userService.findAll());
//        return "index";
//    }


    @GetMapping("/allUsers")
    public String getAllUser(Model model) {
        model.addAttribute("allUsers", userService.findAll());

        findPaginated(1, "id", "asc", model);
//        System.out.println("111 : list = {}", userService.findAll());

        return "user/allUsers";
    }

    @PostMapping("/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/user/allUsers";
    }

    @GetMapping("/newUser")
    public String createUser(Model model) {
        model.addAttribute("newUser", new User());

        return "user/newUser";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("newUser") User user) {

        userService.save(user);
        return "redirect:/user/allUsers";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/update-user";
    }

    @PostMapping("/update")
    public String updateUser(@Valid User user, Model model) {
        userService.updateUser(user);
//        model.addAttribute("user", userService.findAll());
        return "redirect:/user/allUsers";
    }

    @GetMapping("/login")
    public String userLogin(Model model){
        model.addAttribute("newUser", new User());

        return "user/login";
    }

    @GetMapping("/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
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

//    @GetMapping("/test")
//    public String enterUserData(User user){
//        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication();
//        Long usersId = userDetails.getId;
//        System.err.println(usersId);
//        user.getId();
//
//        user.getName();
//        return "user/test";
//    }



//    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
///-------------------------------------


}
