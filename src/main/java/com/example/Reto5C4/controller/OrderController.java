/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Reto5C4.controller;

import com.example.Reto5C4.model.Order;
import com.example.Reto5C4.service.OrderService;
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
 * @author Jeremy
 */
@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/all")
    public List<Order> getAll(){
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable("id") Integer id){
        return orderService.getOrder(id);
    }
    
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order order){
        return orderService.create(order);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Order update(@RequestBody Order order){
        return orderService.update(order);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Integer id){
        return orderService.delete(id);
    } 
    
    @GetMapping("/zona/{zone}")
    public List<Order> getZone(@PathVariable("zone") String zone){
        return orderService.getZone(zone);
    }

    @GetMapping("/salesman/{id}")
    public List<Order> getBySalesManId(@PathVariable("id") Integer id){
        return orderService.getBySalesManId(id);
    }

    @GetMapping("/state/{status}/{id}")
    public List<Order> getByIdSalesManIdAndStatus(@PathVariable("id") Integer id,@PathVariable("status") String status){
        return orderService.getBySalesManIdAndStatus(id, status);
    }

    @GetMapping("/date/{registerDay}/{id}")
    public List<Order> getByRegisterDayAndSalesManId(@PathVariable("registerDay") String registerDay, @PathVariable("id") Integer id){
        return orderService.getByRegisterDayAndSalesManId(registerDay,id);
    }
}
