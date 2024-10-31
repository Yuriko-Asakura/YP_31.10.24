package com.example.pr3_bd.Controller;

import com.example.pr3_bd.enity.Item;
import com.example.pr3_bd.service.ItemService;
import com.example.pr3_bd.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import jakarta.validation.Valid;
import java.util.List;

@Controller
public class ItemController {

    @Qualifier("itemServiceImpl")
    @Autowired
    private ItemService itemService;
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/items")
    public String listItems(Model model) {
        model.addAttribute("items", itemService.findAllItem());
        return "items";
    }

    @GetMapping("/items/add")
    public String showAddItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "addItem";
    }

    @PostMapping("/items/add")
    public String addItem(@Valid @ModelAttribute("item") Item item, BindingResult result) {
        if (result.hasErrors()) {
            return "addItem";
        }
        itemService.createItem(item);
        return "redirect:/items";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.addObject("message", "Ошибка: Предмет с таким именем уже существует. Пожалуйста, выберите другое имя.");
        return modelAndView;
    }

    @GetMapping("/items/edit/{id}")
    public String editItem(@PathVariable Long id, Model model) {
        Item item = itemService.findItembyId(id);
        model.addAttribute("item", item);
        return "editItem";
    }

    @PostMapping("/items/edit")
    public String updateItem(@Valid @ModelAttribute Item item, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            return "editItem";
        }
        itemService.updateItem(item);
        return "redirect:/items";
    }

    @GetMapping("/items/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "redirect:/items";
    }
}