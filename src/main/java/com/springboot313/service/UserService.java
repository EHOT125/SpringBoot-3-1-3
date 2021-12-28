package com.springboot313.service;

import com.springboot313.entities.User;

import java.util.List;

public interface UserService {

    List<User> getList();

    void save(User user);

    User getById(Long id);

    void remove(Long id);
}
