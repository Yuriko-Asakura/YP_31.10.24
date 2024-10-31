package com.example.pr3_bd.Controller;

import com.example.pr3_bd.enity.Item;
import com.example.pr3_bd.enity.Order;
import com.example.pr3_bd.enity.OrderItem;
import com.example.pr3_bd.enity.customer;
import com.example.pr3_bd.service.CustomerService;
import com.example.pr3_bd.service.ItemService;
import com.example.pr3_bd.service.OrderItemService;
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
public class OrderItemController {

    @Qualifier("orderItemServiceImpl")
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/orderItems")
    public String listOrderItems(Model model) {
        List<Item> item = itemService.findAllItem();
        model.addAttribute("items", itemService.findAllItem());
        List<Order> orders = orderService.findAllOrder();
        model.addAttribute("orders", orders);
        model.addAttribute("orderItems", orderItemService.findAllOrderItemr());
        return "orderItems";
    }

    @GetMapping("/orderItems/add")
    public String showAddOrderItemForm(Model model) {
        List<Item> items = itemService.findAllItem();
        model.addAttribute("items", items);
        List<Order> orders = orderService.findAllOrder();
        model.addAttribute("orders", orders);
        model.addAttribute("orderItem", new OrderItem());
        return "addOrderItem";
    }

    @PostMapping("/orderItems/add")
    public String addOrderItem(@Valid @ModelAttribute("orderItem") OrderItem orderItem, BindingResult result) {
        if (result.hasErrors()) {
            return "addOrderItem";
        }
        orderItemService.createOrderItem(orderItem);
        return "redirect:/orderItems";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.addObject("message", "Ошибка: Элемент заказа с такими данными уже существует.");
        return modelAndView;
    }

    @GetMapping("/orderItems/edit/{id}")
    public String editOrderItem(@PathVariable Long id, Model model) {
        List<Item> items = itemService.findAllItem();
        model.addAttribute("items", items);
        List<Order> orders = orderService.findAllOrder();
        model.addAttribute("orders", orders);
        OrderItem orderItem = orderItemService.findOrderItembyId(id);
        model.addAttribute("orderItem", orderItem);
        return "editOrderItem";
    }

    @PostMapping("/orderItems/edit")
    public String updateOrderItem(@Valid @ModelAttribute OrderItem orderItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editOrderItem";
        }
        orderItemService.updateOrderItem(orderItem);
        return "redirect:/orderItems";
    }

    @GetMapping("/orderItems/delete/{id}")
    public String deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return "redirect:/orderItems";
    }
}
