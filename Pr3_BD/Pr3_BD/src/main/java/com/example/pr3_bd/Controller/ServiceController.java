package com.example.pr3_bd.Controller;

import com.example.pr3_bd.enity.Service;
import com.example.pr3_bd.service.ServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ServiceController {

    @Autowired
    private ServiceService serviceService;


    @GetMapping("/services")
    public String listServices(Model model) {
        List<Service> services = serviceService.findAllService();
        model.addAttribute("services", services);
        return "services";
    }

    @GetMapping("/services/add")
    public String showAddForm(Model model) {
        model.addAttribute("service", new Service());
        return "services";
    }

    @PostMapping("/services/add")
    public String addService(@Valid @ModelAttribute Service service, BindingResult result) {
        if (result.hasErrors()) {
            return "services";
        }
        serviceService.createService(service);
        return "redirect:/services";
    }

    @GetMapping("/services/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Service service = serviceService.findServicebyId(id);
        model.addAttribute("service", service);
        return "services";
    }

    @PostMapping("/services/edit/{id}")
    public String updateService(@PathVariable Long id, @Valid @ModelAttribute Service service, BindingResult result) {
        if (result.hasErrors()) {
            return "services";
        }
        service.setId(id);
        serviceService.createService(service);
        return "redirect:/services";
    }

    @GetMapping("/services/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return "redirect:/services";
    }
}
