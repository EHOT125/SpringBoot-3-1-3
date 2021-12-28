package com.springboot313.controllers;


import com.springboot313.entities.User;
import com.springboot313.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final UserService userService;

    public ViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String restAdmin(Model model) {
        User user = (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()) ;
        model.addAttribute("user", user);
        return "/admin/adminTemplate";
    }

    @GetMapping("/user")
    public String restUser(Model model) {
        User user = (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()) ;
        model.addAttribute("user", user);
        return "/user/userTemplate";
    }
}
