/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incident_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incident_management.entity.User;
import com.incident_management.exception.UserNotFoundException;
import com.incident_management.service.UserService;
import com.incident_management.service.helpers.UserInfos;
import com.incident_management.service.helpers.UserLoginResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.incident_management.utils.Util;

/**
 *
 * @author user
 */
@SuppressWarnings("rawtypes")
@RequestMapping(value = "/user")
@CrossOrigin("*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/open/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserInfos userInfos, HttpSession session) {
        User user = userService.findUserByUsernameAndPassword(userInfos.getUsername(), Util.encryptPassword(userInfos.getPassword()));
        if (user != null) {
            session.setAttribute("user", user);
            String sessionId = session.getId();
            Long userId = user.getId();
            UserLoginResponse response = new UserLoginResponse(user.getUsername(), userId, sessionId, "Login successful");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserLoginResponse(null, null, null, "Invalid credentials"));
        }
    }

    @PostMapping("/open/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        user.setPassword(Util.encryptPassword(user.getPassword()));
        userService.save(user);
        return ResponseEntity.ok("User added successfully");
    }

    @DeleteMapping("/secured/delete/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable("id") long id) {
        try {
            userService.deleteByID(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/secured/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        try {
            System.out.println("IDController--------> " + user.getId());
            userService.update(user);
            return ResponseEntity.ok("User updated successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/secured/find")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/secured/find/{id}")
    public ResponseEntity<User> findByID(@PathVariable("id") long id) {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
