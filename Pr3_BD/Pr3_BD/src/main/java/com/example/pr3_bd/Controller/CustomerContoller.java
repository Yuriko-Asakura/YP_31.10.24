package com.example.pr3_bd.Controller;

import com.example.pr3_bd.enity.Order;
import com.example.pr3_bd.enity.Profile;
import com.example.pr3_bd.enity.customer;
import com.example.pr3_bd.service.CustomerService;
import com.example.pr3_bd.service.OrderService;
import com.example.pr3_bd.service.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
//@PreAuthorize("hasAnyAuthority('USER')")
@Controller
public class CustomerContoller {

    @Qualifier("customerServiceImpl")
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/customers")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.findAllCustomer());
        return "customers";
    }
    @GetMapping("/Spisok")
    public  String listSpisok(){
        return "Spisok";
    }
    @PostMapping("/Spisok")
    public  String listSpisok_post(){
        return "Spisok";
    }
    @GetMapping("/customers/add")
    public String showAddCustomerForm(Model model) {
        List<Profile> profile = profileService.findAllProfile();
        List<Order> order = orderService.findAllOrder();
        List<customer> customersList = customerService.findAllCustomer();
        model.addAttribute("customer", new customer());
        model.addAttribute("profiles", profile);
        model.addAttribute("orders", order);
        model.addAttribute("student", new customer());
        return "addCustomer";
    }

    @PostMapping("/customers/add")
    public String addCustomer(@Valid @ModelAttribute("customer") customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "addCustomer";
        }
        customerService.createCustomer(customer);
        return "redirect:/customers";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.addObject("message", "Ошибка: клиент с таким профилем уже существует");
        return modelAndView;
    }


    @GetMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        customer customer1 = customerService.findCustomerbyId(id);
        model.addAttribute("customer", customer1);
        List<Profile> profiles = profileService.findAllProfile();
        model.addAttribute("profiles", profiles);

        return "editCustomer"; 
    }


    @PostMapping("/customers/edit")
    public String updateCustomer(@Valid @ModelAttribute customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            List<customer> cust = customerService.findAllCustomer();
            List<Profile> pr = profileService.findAllProfile();
            model.addAttribute("cust", cust);
            model.addAttribute("pr", pr);
            return "editCustomer";
        }
        customerService.updateCustomer(customer);
        return "redirect:/customers";
    }


    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}

