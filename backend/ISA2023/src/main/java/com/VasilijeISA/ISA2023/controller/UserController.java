package com.VasilijeISA.ISA2023.controller;

import com.VasilijeISA.ISA2023.dto.UpdateUserDto;
import com.VasilijeISA.ISA2023.model.User;
import com.VasilijeISA.ISA2023.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/activate-account")
    public String activateAccount(@RequestParam (name = "token", required = false) String token, Model model) {
        User user = userService.activateAccount(token);
        if (user != null) {
            model.addAttribute("message", "Nalog je uspešno aktiviran!");
        } else {
            model.addAttribute("message", "Nevažeći aktivacioni token!");
        }
        return "activation-result";
    }

    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestBody UpdateUserDto u){
        User user = userService.update(u);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
