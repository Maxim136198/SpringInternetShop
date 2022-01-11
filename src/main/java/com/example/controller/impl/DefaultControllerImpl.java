package com.example.controller.impl;


import com.example.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class DefaultControllerImpl {

    private ProductService productService;

    @GetMapping("/")
    public String sayIndex(Model model){
//            model.addAttribute("allProducts", productService.findAll());
        return "index";
    }
}
