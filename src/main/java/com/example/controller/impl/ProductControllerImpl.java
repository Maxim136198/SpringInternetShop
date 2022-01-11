package com.example.controller.impl;


import com.example.controller.ProductController;
import com.example.dao.entity.Product;
import com.example.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductControllerImpl implements ProductController {

    private ProductService productService;

    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/allProduct")
    public String getAllProduct(Model model) {
        model.addAttribute("allProducts", productService.findAll());
        return "product/allProduct";
    }

//    @GetMapping("/allProduct")
//    public String getAllProductByCategory(Model model) {
//        model.addAttribute("allProductsByCategory", productService.findAllByCategory());
//        return "product/allProduct";
//    }


    @PostMapping("/{id}")
    public String removeProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/product/allProduct";
    }

    @GetMapping("/newProduct")
    public String createProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "product/newProduct";
    }

    @PostMapping()
    public String saveProduct(@ModelAttribute("newProduct") Product product) {
        productService.save(product);
        return "redirect:/allProduct";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/update-product";
    }

    @PostMapping("/update")
    public String updateProduct(@Valid Product product) {
        productService.updateProduct(product);
        return "redirect:/product/allProduct";
    }
}
