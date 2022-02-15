package com.example.controller.impl;

import com.example.dao.entity.User;
import com.example.dao.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Controller
//@RequestMapping("/")
public class RegistrationControllerImpl {

    private UserRepository userRepository;

    @GetMapping("/registration")
    public String userLogin(){
        return "user/newUser";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> model){
        Optional<User> userFromDb = userRepository.findByName(user.getName());

        if (userFromDb != null) {
            model.put("message", "User exists");
            return "user/registration";
        }

        return "redirect:/login";
    }

}
