package com.example.controller.impl;


import com.example.controller.ProductController;
import com.example.dao.entity.Product;
import com.example.service.ProductService;
import com.example.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductControllerImpl implements ProductController {

    private ProductService productService;
    private UserService userService;

    public ProductControllerImpl(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/allProduct")
    public String getAllProduct(Model model) {
        model.addAttribute("allProducts", productService.findAll());

        findPaginated(1, "id", "asc", model);

        model.addAttribute("user", userService.getUserName());
        return "product/listProducts";
    }


    @PostMapping("/{id}")
    public String removeProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/product/listProducts";
    }

    @GetMapping("/newProduct")
    public String createProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "product/newProduct";
    }

    @PostMapping()
    public String saveProduct(@ModelAttribute("newProduct") Product product) {
        productService.save(product);
        return "redirect:/product/listProducts";
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
        return "redirect:/product/listProducts";
    }

    @GetMapping("/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Product> page = productService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Product> listProduct = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("allProducts", listProduct);
        return "product/listProducts";
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

}
