package com.example.sboot.service;

import com.example.sboot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Object findByUsername(String username);

    Object findAll();

    Optional<User> findById(Integer id);

    User findById(int id);

    void save(User user);

    void delete(int id);

    void updateUser(int id, User user);
}
