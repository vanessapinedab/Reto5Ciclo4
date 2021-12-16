/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Reto5C4.service;

import com.example.Reto5C4.model.User;
import com.example.Reto5C4.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jeremy
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> getUser(Integer id) {
        return userRepository.getUser(id);
    }

    public User save(User user) {
        if (user.getId() != null) {
            Optional<User> usuario = userRepository.getUser(user.getId());
            if (usuario.isEmpty()) {
                if (userRepository.ExisteEmail(user.getEmail()) == false) {
                    return userRepository.save(user);
                } else {
                    return user;
                }
            } else {
                return user;
            }
        } else {
            return user;
        }
    }
    
    public User update(User user){
        if(user.getId() != null){
            Optional<User> usuario = userRepository.getUser(user.getId());
            if (!usuario.isEmpty()){
                if(user.getIdentification() !=null){
                    usuario.get().setIdentification(user.getIdentification());   
                }
                if(user.getName() !=null){
                    usuario.get().setName(user.getName());
                }
                if(user.getAddress() !=null){
                    usuario.get().setAddress(user.getAddress());
                }
                if(user.getCellPhone() !=null){
                    usuario.get().setCellPhone(user.getCellPhone());
                }
                if(user.getEmail() !=null){
                    usuario.get().setEmail(user.getEmail());
                }
                if(user.getPassword() !=null){
                    usuario.get().setPassword(user.getPassword());
                }
                if(user.getZone() !=null){
                    usuario.get().setZone(user.getZone());
                }
                if(user.getType() !=null){
                    usuario.get().setType(user.getType());
                }
                userRepository.update(usuario.get());
                return usuario.get();            
            }
        }else{
            return user;
        }
        return user;
    }
    
    public boolean delete(Integer id){
        boolean usuario= getUser(id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
        return usuario;
    }
    
    public boolean ExiteEmail(String email){
        return userRepository.ExisteEmail(email);
    }
    
    public User autenticarUser(String email, String password){
        Optional<User> usuario = userRepository.autenticarUser(email, password);
        
        if(usuario.isEmpty()){
            return new User();
        }else{
            return usuario.get();
        }
    }
    
    
    public List <User> findByMonthBirthtDay (String birthday){
        return  userRepository.findByMonthBirthtDay(birthday);
    }
    
}

