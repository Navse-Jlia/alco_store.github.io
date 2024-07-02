package com.vasilkov.web.controller;

import com.vasilkov.model.dto.StoreProductDto;
import com.vasilkov.web.rabbit.producer.StoreProductProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

/**
 * The type Main controller.
 *
 * @author Artem Vasilkov
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    private final StoreProductProducer producer;

    /**
     * Show main page string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping
    public String showMainPage(Model model) {
        List<StoreProductDto> products = producer.getAll();
        model.addAttribute("product", products);
        return "index.html";
    }

    /**
     * Show all products string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/products")
    public String showAllProducts(Model model) {
        List<StoreProductDto> products = producer.getAll();
        model.addAttribute("product", products);
        return "pages/catalog.html";
    }

    /**
     * Show product string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/products/{id}")
    public String showProduct(@PathVariable UUID id, Model model) {
        StoreProductDto product = producer.getById(id);
        model.addAttribute("product", product);
        return "pages/product.html";
    }

    /**
     * Show order create page string.
     *
     * @return the string
     */
    @GetMapping("/catalog/order-form")
    public String showOrderCreatePage() {
        return "pages/new_order.html";
    }

    /**
     * Show product create page string.
     *
     * @return the string
     */
    @GetMapping("/user/orders/product-form")
    public String showProductCreatePage() {
        return "pages/new_product.html";
    }

}
