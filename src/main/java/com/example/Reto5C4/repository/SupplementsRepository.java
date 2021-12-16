/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Reto5C4.repository;

import com.example.Reto5C4.model.Supplements;
import com.example.Reto5C4.repository.Interface.SupplementsInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jeremy
 */
@Repository
public class SupplementsRepository {
    
    @Autowired
    private SupplementsInterface supplementsInterface;

    public List<Supplements> getAll() {
        return supplementsInterface.findAll();
    }

    public Optional<Supplements> getSupplement(Integer reference) {
        return supplementsInterface.findById(reference);
    }
    public Supplements create(Supplements supplements) {
        return supplementsInterface.save(supplements);
    }

    public void update(Supplements supplements) {
        supplementsInterface.save(supplements);
    }
    
    public void delete(Supplements supplements) {
        supplementsInterface.delete(supplements);
    }
    
    public List <Supplements> findByPrice (double price){
        return  supplementsInterface.findByPrice(price);
    }
    

    
}
