package com.springboot313.service;


import com.springboot313.entities.User;
import com.springboot313.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    
    
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username);
    }

    @Transactional(readOnly = true)
    public List<User> getList() {
        return userRepository.findAll();
    }
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
    @Transactional(readOnly = true)
    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }
    @Transactional
    public void remove(Long id) {
        userRepository.delete(getById(id));
    }
}
