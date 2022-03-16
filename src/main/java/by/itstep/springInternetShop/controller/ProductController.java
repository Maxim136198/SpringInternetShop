package by.itstep.springInternetShop.controller;

import by.itstep.springInternetShop.dao.entity.Product;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface ProductController {

    String getAllProduct(Model model);

    String removeProduct(@PathVariable("id") Long id);

    String createProduct(Model model);

    String saveProduct(@ModelAttribute("newProduct") Product product);

    String showUpdateForm(@PathVariable("id") long id, Model model);

    String updateProduct(@Valid Product product);

    String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                         @RequestParam("sortField") String sortField,
                         @RequestParam("sortDir") String sortDir,
                         Model model);

    String viewHomePage(Model model);
}
