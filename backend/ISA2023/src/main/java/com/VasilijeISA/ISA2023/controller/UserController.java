package com.VasilijeISA.ISA2023.controller;

import com.VasilijeISA.ISA2023.dto.UpdateUserDto;
import com.VasilijeISA.ISA2023.model.User;
import com.VasilijeISA.ISA2023.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestBody UpdateUserDto u){
        User user = userService.update(u);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
