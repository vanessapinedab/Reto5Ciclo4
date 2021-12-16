/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Reto5C4.repository.Interface;

import com.example.Reto5C4.model.Supplements;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Jeremy
 */
public interface SupplementsInterface extends MongoRepository<Supplements, Integer> {
    
    public List <Supplements> findByPrice (double price);
    
    
    
}
