package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    
    User getUserByUsername(String username);
    void save(User user);
    void update(Long id, User user);
    void delete(long id);
    List<User> getUsers();
    User getUserById(long id);
}
