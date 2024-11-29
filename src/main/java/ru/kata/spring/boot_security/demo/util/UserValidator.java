package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Component
public class UserValidator implements Validator {
    
    private final UserService userService;
    
    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (user.getRoles().isEmpty()) {
            errors.rejectValue("roles", "", "Need choose role");
        }
        
        User existiongUser = userService.getUserByUsername(user.getEmail());
        if (existiongUser != null && !existiongUser.getId().equals(user.getId())) {
            errors.rejectValue("email", "", "User with this email already exist");
        }
        
    }
}
