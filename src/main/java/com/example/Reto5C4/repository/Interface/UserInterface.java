/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Reto5C4.repository.Interface;


import com.example.Reto5C4.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Jeremy
 */
public interface UserInterface extends MongoRepository<User, Integer> {
     Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String password);
    
    public List <User> findByMonthBirthtDay (String birthday);
}

