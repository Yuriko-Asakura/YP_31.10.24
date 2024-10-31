//package com.example.pr3_bd.Controller;
//
//import com.example.pr3_bd.enity.ModelUser;
//import com.example.pr3_bd.enity.RoleEnum;
//import com.example.pr3_bd.repository.UserRepository;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.Collections;
//
//@Controller
//public class RegistrationController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @GetMapping("/registration")
//    public String regView() {
//        return "regis";
//    }
//
//    @PostMapping("/registration")
//    public String reg(@Valid @ModelAttribute ModelUser  user, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("message", "Пожалуйста, исправьте ошибки в форме.");
//            return "regis";
//        }
//        if (userRepository.existsByUsername(user.getUsername())) {
//            model.addAttribute("message", "Пользователь с таким логином уже существует");
//            return "regis";
//        }
//            user.setActive(true);
//            user.setRoles(Collections.singleton(RoleEnum.USER));
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userRepository.save(user);
//            return "redirect:/login";
//        }
//}
