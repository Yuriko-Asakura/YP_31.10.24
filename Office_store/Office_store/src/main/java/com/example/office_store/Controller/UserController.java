package com.example.office_store.Controller;

import com.example.office_store.model.*;
import com.example.office_store.repository.CategoryRepo;
import com.example.office_store.repository.ProfileRepo;
import com.example.office_store.repository.SupRepo;
import com.example.office_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private SupRepo supRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String userView(Model model) {
        model.addAttribute("user_list", userRepository.findAll());
        return "index";
    }

    @GetMapping("/users_view")
    public String userViews(Model model) {
        model.addAttribute("user_list", userRepository.findAll());
        return "index_view";
    }

    @GetMapping("/{id}")
    public String detailView(@PathVariable Long id, Model model) {
        ModelUser user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user_object", user);
        return "info";
    }

    @GetMapping("/{id}/update")
    public String updView(@PathVariable Long id, Model model) {
        ModelUser user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user_object", user);
        model.addAttribute("roles", RoleEnum.values());
        return "update";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable Long id,
                             @RequestParam String username,
                             @RequestParam(name = "roles[]", required = false) String[] roles) {
        ModelUser user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        user.setUsername(username);

        user.getRoles().clear();
        if (roles != null) {
            for (String role : roles) {
                user.getRoles().add(RoleEnum.valueOf(role));
            }
        }

        userRepository.save(user);
        return "redirect:/admin/" + id;
    }

    @GetMapping("/user_view_user")
    public String user_Views(Model model) {
        model.addAttribute("user_list", userRepository.findAll());
        return "user_view";
    }


    @GetMapping("/prof_view")
    public String prof_Views(Model model) {
        model.addAttribute("prof_list", profileRepo.findAll());
        return "prof_view";
    }

    @GetMapping("/prof/{id}/update")
    public String updateProfileView(@PathVariable Long id, Model model) {
        model_Profile profile = profileRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid profile ID: " + id));
        model.addAttribute("profile_object", profile);
        return "profile_update";

    }


    @PostMapping("/prof/{id}/update")
    public String updateProfile(@PathVariable Long id,
                                @RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam Long userId) {
        model_Profile profile = profileRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid profile ID: " + id));
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        ModelUser  user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
        profile.setUser_id(user);

        profileRepo.save(profile);
        return "redirect:/admin/prof_view";
    }

    @GetMapping("/prof/{id}/delete")
    public String deleteProfile(@PathVariable Long id) {
        profileRepo.deleteById(id);
        return "redirect:/admin/prof_view";
    }
    @PostMapping("/prof/create")
    public String createProfile(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam Long userId) {
        ModelUser  user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));

        model_Profile newProfile = new model_Profile();
        newProfile.setFirstName(firstName);
        newProfile.setLastName(lastName);
        newProfile.setUser_id(user);

        profileRepo.save(newProfile);
        return "redirect:/prof_view";
    }

    @GetMapping("/prof/create")
    public String showCreateProfileForm(Model model) {
        model.addAttribute("prof_list", userRepository.findAll());
        return "profile_create";
    }



    @GetMapping("/cat_view")
    public String cat_Views(Model model) {
        model.addAttribute("cat_list", categoryRepo.findAll());
        return "cat_view";
    }
    @GetMapping("/cat/{id}/update")
    public String updateCategoryView(@PathVariable Long id, Model model) {
        model_Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));
        model.addAttribute("category_object", category);
        return "category_update";
    }

    @PostMapping("/cat/{id}/update")
    public String updateCategory(@PathVariable Long id,
                                 @RequestParam String name) {
        model_Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));
        category.setName(name);

        categoryRepo.save(category);
        return "redirect:/admin/category_update/" + id;
    }

    @GetMapping("/cat/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        categoryRepo.deleteById(id);
        return "redirect:/admin/cat_view";
    }

    @GetMapping("/cat/create")
    public String showCreateCategoryForm() {
        return "category_create";
    }

    @PostMapping("/cat/create")
    public String createCategory(@RequestParam String name) {
        model_Category newCategory = new model_Category();
        newCategory.setName(name);

        categoryRepo.save(newCategory);
        return "redirect:/admin/cat_view";
    }


    @GetMapping("/sup_view")
    public String sup_Views(Model model) {
        model.addAttribute("sup_list", supRepo.findAll());
        return "sup_view";
    }
    @GetMapping("/sup/{id}/update")
    public String updateSupplierView(@PathVariable Long id, Model model) {
        model_Supplier supplier = supRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid supplier ID: " + id));
        model.addAttribute("supplier_object", supplier);
        return "supplier_update";
    }

    @PostMapping("/sup/{id}/update")
    public String updateSupplier(@PathVariable Long id,
                                 @RequestParam String name,
                                 @RequestParam String contactInfo) {
        model_Supplier supplier = supRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid supplier ID: " + id));
        supplier.setName(name);
        supplier.setContactInfo(contactInfo);

        supRepo.save(supplier);
        return "redirect:/admin/sup_view";
    }

    @GetMapping("/sup/{id}/delete")
    public String deleteSupplier(@PathVariable Long id) {
        supRepo.deleteById(id);
        return "redirect:/admin/sup_view";
    }
    @GetMapping("/sup/create")
    public String showCreateSupplierForm() {
        return "supplier_create";
    }

    @PostMapping("/sup/create")
    public String createSupplier(@RequestParam String name,
                                 @RequestParam String contactInfo) {
        model_Supplier newSupplier = new model_Supplier();
        newSupplier.setName(name);
        newSupplier.setContactInfo(contactInfo);

        supRepo.save(newSupplier);
        return "redirect:/admin/sup_view";
    }

}
