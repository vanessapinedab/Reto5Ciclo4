/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Reto5C4.controller;

import com.example.Reto5C4.model.User;
import com.example.Reto5C4.service.UserService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Waldo
 */
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/all")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") Integer id){
        return userService.getUser(id);
    }
    
    @GetMapping("/emailexist/{email}")
    public boolean existeEmail(@PathVariable("email") String email){
        return userService.ExiteEmail(email);
    }
    
    @GetMapping("/{email}/{password}")
    public User autenticarUser(@PathVariable("email")String email, @PathVariable("password") String password){
        return userService.autenticarUser(email, password);
    }
    
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user){
        return userService.save(user);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user){
        return userService.update(user);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Integer id){
        return userService.delete(id);
    }
    
    
    @GetMapping("/birthday/{birthday}")
    public List <User> findByMonthBirthtDay(@PathVariable("birthday") String birthday){
        return userService.findByMonthBirthtDay(birthday);
    }
    
}
