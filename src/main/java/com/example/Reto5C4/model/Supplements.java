/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Reto5C4.model;

/**
 *
 * @author Jeremy
 */

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clone")
public class Supplements {
    @Id
    private Integer id;
    private String brand;
    private String procesor;
    private String os;
    private String description;
    private String memory;
    private String hardDrive;
    private boolean availability = true;
    private double price;
    private int quantity;
    private String photography;
}
