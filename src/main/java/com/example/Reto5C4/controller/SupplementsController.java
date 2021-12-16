/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Reto5C4.controller;

import com.example.Reto5C4.model.Supplements;
import com.example.Reto5C4.service.SupplementsService;
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
@RequestMapping("/clone")
@CrossOrigin("*")
public class SupplementsController {
    @Autowired
    private SupplementsService supplementsService;
       
    @GetMapping("/all")
    public List<Supplements> getAll() {
        return supplementsService.getAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Supplements> getSupplement(@PathVariable("id") Integer id) {
        return supplementsService.getSupplement(id);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Supplements save(@RequestBody Supplements supplements) {
        return supplementsService.save(supplements);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Supplements update(@RequestBody Supplements supplements) {
        return supplementsService.update(supplements);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Integer id) {
        return supplementsService.delete(id);
    }
    
    @GetMapping("/price/{price}")
    public List<Supplements> findByPrice(@PathVariable("price") double price) {
        return supplementsService.findByPrice(price);
    }
    
    @GetMapping("/description/{description}")
    public List<Supplements> findByDescription(@PathVariable("description") String description) {
        return supplementsService.findByDescription(description);
    }  
    
}
