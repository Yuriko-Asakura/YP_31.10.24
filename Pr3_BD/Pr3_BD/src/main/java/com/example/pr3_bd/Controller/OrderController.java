package com.example.pr3_bd.Controller;

import com.example.pr3_bd.enity.Order;
import com.example.pr3_bd.enity.customer;
import com.example.pr3_bd.service.CustomerService;
import com.example.pr3_bd.service.OrderService;
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
public class OrderController {

    @Qualifier("orderServiceImpl")
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;
    @GetMapping("/orders")
    public String listOrders(Model model) {
        List<customer> customers = customerService.findAllCustomer();
        model.addAttribute("customers", customers);
        model.addAttribute("orders", orderService.findAllOrder());
        return "orders";
    }

    @GetMapping("/orders/add")
    public String showAddOrderForm(Model model) {
        model.addAttribute("order", new Order());
        List<customer> customers = customerService.findAllCustomer();
        model.addAttribute("customers", customers);
        return "addOrder";
    }

    @PostMapping("/orders/add")
    public String addOrder(@Valid @ModelAttribute("order") Order order, BindingResult result) {
        if (result.hasErrors()) {
            return "addOrder";
        }
        orderService.createOrder(order);
        return "redirect:/orders";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.addObject("message", "Ошибка: Заказ с такими данными уже существует.");
        return modelAndView;
    }

    @GetMapping("/orders/edit/{id}")
    public String editOrder(@PathVariable Long id, Model model) {
        Order order = orderService.findOrderbyId(id);
        List<customer> customers = customerService.findAllCustomer();
        model.addAttribute("customers", customers);
        model.addAttribute("order", order);
        return "editOrder";
    }

    @PostMapping("/orders/edit")
    public String updateOrder(@Valid @ModelAttribute Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editOrder";
        }
        orderService.updateOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}