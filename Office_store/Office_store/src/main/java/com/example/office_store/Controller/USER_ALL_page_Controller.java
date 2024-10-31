package com.example.office_store.Controller;

import com.example.office_store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('USER')")
@Controller
public class USER_ALL_page_Controller {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public String product(Model model){
        model.addAttribute("product_list", productRepository.findAll());
        return "product_view";
    }
}
