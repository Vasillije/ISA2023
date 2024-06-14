package com.VasilijeISA.ISA2023.controller;

import com.VasilijeISA.ISA2023.Utils.TokenUtils;
import com.VasilijeISA.ISA2023.dto.JwtAuthenticationRequestDto;
import com.VasilijeISA.ISA2023.dto.UserRequestDto;
import com.VasilijeISA.ISA2023.dto.UserTokenStateDto;
import com.VasilijeISA.ISA2023.exeption.UserAlreadyExistsException;
import com.VasilijeISA.ISA2023.model.User;
import com.VasilijeISA.ISA2023.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    public UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenStateDto> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequestDto authenticationRequest, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwt;
        if( user.getCompany()!=null){
         jwt = tokenUtils.generateToken(user.getName(), user.getSurname() ,user.getId(), user.getCompany().getId());
        }
        else {
             jwt = tokenUtils.generateToken(user.getName(), user.getSurname() ,user.getId(), 0L);

    }
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenStateDto(jwt, expiresIn));
    }

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody UserRequestDto userRequest, UriComponentsBuilder ucBuilder) {
       // User existUser = this.userService.findByEmail(userRequest.getEmail());

        //if (existUser != null) {
        //    throw new ResourceConflictException(userRequest.getId(), "Username already exists");
       // }

        User user = this.userService.save(userRequest);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
