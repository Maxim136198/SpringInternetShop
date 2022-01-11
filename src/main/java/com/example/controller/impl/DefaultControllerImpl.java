package com.example.controller.impl;


import com.example.service.ProductService;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class DefaultControllerImpl {

    private ProductService productService;

    @GetMapping("/{inpfilename}")
    public String sayIndex(Model model,@PathVariable String inpfilename ){
        String filename = inpfilename;
        System.out.println(inpfilename);


//            model.addAttribute("allProducts", productService.findAll());
        return "home/"+filename;
    }
}
