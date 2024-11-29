package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    
    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator userValidator;
    
    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserValidator userValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidator = userValidator;
    }
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "admin/index";
    }
    
    @GetMapping(value = "/new")
    public String newUser(Model model) {
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("user", new User());
        return "admin/new";
    }

    @PostMapping(value = "/save")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        
        model.addAttribute("roles", roleService.getRoles());
        userValidator.validate(user, bindingResult);
        
        if (bindingResult.hasErrors()) {
            return "admin/new";
        }
        
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRoles());
        return "admin/edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String update(@ModelAttribute(name = "user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id, Model model) {
        
        model.addAttribute("roles", roleService.getRoles());
        userValidator.validate(user, bindingResult);
        
        if (bindingResult.hasErrors()) {
            return "admin/edit";
        }
        
        userService.update(id, user);
        return "redirect:/admin";
    }
    
    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
