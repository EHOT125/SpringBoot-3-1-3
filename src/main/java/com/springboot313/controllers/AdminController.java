package com.springboot313.controllers;


import com.springboot313.entities.User;
import com.springboot313.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/rest/admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;

    public AdminController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("users")
    public List<User> getAllUsers() {
        return userServiceImpl.getList();
    }

    @PostMapping("/users")
    public void addNewUser(@RequestBody User user) {
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            user.setPassword(bCrypt(user.getPassword()));
        } else {
            user.setPassword(user.getPassword());
        }
        userServiceImpl.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            user.setPassword(bCrypt(user.getPassword()));
        } else {
            user.setPassword(user.getPassword());
        }
        userServiceImpl.loadUserByUsername("user");
        try {
            userServiceImpl.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userServiceImpl.remove(id);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long id) {
        try {
            User user = userServiceImpl.getById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    private String bCrypt(String hash) {
        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        return crypt.encode(hash);
    }
}
