package com.niit.qlsv.controller;

import com.niit.qlsv.dao.dto.UserDto;
import com.niit.qlsv.dao.entities.UserEnt;
import com.niit.qlsv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDto> findUserByUsername(@RequestParam(name = "username") String username) {
        return new ResponseEntity<UserDto>(userService.findUserByUsername(username).getAsDto(), HttpStatus.OK);
    }


}
