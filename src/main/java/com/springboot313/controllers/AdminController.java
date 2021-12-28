package com.springboot313.controllers;


import com.springboot313.entities.User;
import com.springboot313.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/rest/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public List<User> getAllUsers() {
        return userService.getList();
    }

    @PostMapping("/users")
    public void addNewUser(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.remove(id);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long id) {
        try {
            User user = userService.getById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
