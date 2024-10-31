package com.example.pr3_bd.Controller;

import com.example.pr3_bd.enity.Item;
import com.example.pr3_bd.enity.Service;
import com.example.pr3_bd.repository.ItemRepository1;
import com.example.pr3_bd.repository.ServiceRepository;
import com.example.pr3_bd.repository.ServiceRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ManyController {
    @Autowired
    private ItemRepository1 itemRepository1;
    @Autowired
    private ServiceRepository1 serviceRepository1;

    @GetMapping("/man")
    private String Man(Model model) {
        Iterable<Service> services = serviceRepository1.findAll();
        model.addAttribute("services", services);
        Iterable<Item> items = itemRepository1.findAll();
        model.addAttribute("items", items);
        return "man";
    }

    @PostMapping("/man")
    private String Man_list(Model model) {
        Iterable<Service> services = serviceRepository1.findAll();
        model.addAttribute("services", services);
        Iterable<Item> items = itemRepository1.findAll();
        model.addAttribute("items", items);
        return "man";
    }

    @PostMapping("/many/add")
    public String blogPostAdd(@RequestParam String services2, @RequestParam String item1, Model model) {
        Item item2 = itemRepository1.findByItemName(item1);
        Service service1 = serviceRepository1.findByserviceName(services2);
        item2.getServices().add(service1);
        service1.getItems().add(item2);
        itemRepository1.save(item2);
        serviceRepository1.save(service1);
        Iterable<Service> services = serviceRepository1.findAll();
        model.addAttribute("services", services);
        Iterable<Item> items = itemRepository1.findAll();
        model.addAttribute("items", items);
        return "many";
    }

    @GetMapping("/many/add")
    private String Mans(Model model) {
        Iterable<Service> services = serviceRepository1.findAll();
        model.addAttribute("services", services);
        Iterable<Item> items = itemRepository1.findAll();
        model.addAttribute("items", items);

        model.addAttribute("items", itemRepository1.findAll());
        model.addAttribute("services", serviceRepository1.findAll());
        return "many";
    }

    @PostMapping("/man/edit")
    public String Many_edit(@ModelAttribute Service service, @RequestParam(value = "items", required = false) List<Long> Items_id) {
        if(Items_id !=null)
        {
            List<Item> selecdServers = (List<Item>) itemRepository1.findAllById(Items_id);
            service.setItemList(selecdServers);
        }
        return "many";
    }
}
