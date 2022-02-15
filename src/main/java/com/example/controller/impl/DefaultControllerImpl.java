package com.example.controller.impl;


import com.example.service.OrderService;
import com.example.service.ProductService;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class DefaultControllerImpl {

    private ProductService productService;

    private OrderService orderService;

//    @GetMapping("/{inpfilename}")
//    public String sayIndex(Model model,@PathVariable String inpfilename ){
//        String filename = inpfilename;
//        System.out.println(inpfilename);
//
//
////            model.addAttribute("allProducts", productService.findAll());
//        return "home/"+filename;
//    }

    @GetMapping("/")
    public String sayIndex(Model model, Principal principal) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) auth.getPrincipal();
//        String userLogin = userDetails.getUsername();
        String userLogin;
        if (principal != null && principal.getName() != null) {
            userLogin = principal.getName();
        } else {
            userLogin = "Guest";
        }
        model.addAttribute("userName", userLogin);
        return "home/index";
    }

}
