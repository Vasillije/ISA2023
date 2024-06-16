package com.VasilijeISA.ISA2023.controller;

import com.VasilijeISA.ISA2023.model.User;
import com.VasilijeISA.ISA2023.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class ActivationController {
    @Autowired
    UserService userService;
    @GetMapping("/activate-account")
    public String activateAccount(@RequestParam(name = "token", required = false) String token, Model model) {
        User user = userService.activateAccount(token);
        if (user != null) {
            model.addAttribute("message", "Nalog je uspešno aktiviran!");
        } else {
            model.addAttribute("message", "Nevažeći aktivacioni token!");
        }
        return "activation-result";
    }
}
