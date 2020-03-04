package com.niit.qlsv.controller;

import com.niit.qlsv.dao.entities.LoginResponse;
import com.niit.qlsv.dao.entities.UserEnt;
import com.niit.qlsv.jwt.JwtTokenUtil;
import com.niit.qlsv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserEnt user) throws Exception {
        // Authenticate user
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            String token = jwtTokenUtil.generateToken(userService.loadUserByUsername(user.getUsername()));

            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(user.getUsername(), token));
        } catch (Exception e) {
            return new ResponseEntity<String>("Invalid Login", HttpStatus.BAD_REQUEST);
        }
    }
}
