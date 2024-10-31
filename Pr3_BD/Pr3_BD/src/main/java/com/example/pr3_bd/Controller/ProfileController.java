package com.example.pr3_bd.Controller;

import com.example.pr3_bd.enity.Profile;
import com.example.pr3_bd.service.ProfileService;
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

@Controller
public class ProfileController {

    @Qualifier("profileServiceImpl")
    @Autowired
    private ProfileService profileService;

    @GetMapping("/profiles")
    public String listProfiles(Model model) {
        model.addAttribute("profiles", profileService.findAllProfile());
        return "profiles";
    }

    @GetMapping("/profiles/add")
    public String showAddProfileForm(Model model) {
        model.addAttribute("profile", new Profile());
        return "addProfile";
    }

    @PostMapping("/profiles/add")
    public String addProfile(@Valid @ModelAttribute("profile") Profile profile, BindingResult result) {
        if (result.hasErrors()) {
            return "addProfile";
        }
        profileService.createProfile(profile);
        return "redirect:/profiles";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.addObject("message", "Ошибка: Профиль с такими данными уже существует.");
        return modelAndView;
    }

    @GetMapping("/profiles/edit/{id}")
    public String editProfile(@PathVariable Long id, Model model) {
        Profile profile = profileService.findProfilebyId(id);
        model.addAttribute("profile", profile);
        return "editProfile";
    }

    @PostMapping("/profiles/edit")
    public String updateProfile(@Valid @ModelAttribute Profile profile, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editProfile";
        }
        profileService.updateProfile(profile);
        return "redirect:/profiles";
    }

    @GetMapping("/profiles/delete/{id}")
    public String deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return "redirect:/profiles";
    }
}
