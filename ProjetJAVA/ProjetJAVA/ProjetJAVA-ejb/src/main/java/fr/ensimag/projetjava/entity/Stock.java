/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;

import javax.persistence.Entity;

/**
 *
 * @author josib
 */
@Entity
public class Stock extends Asset {
    String stockCode;
    
    public Stock() {
        super();
    }
    
    public Stock(String name, String stockCode) {
        this.name = name;
        this.stockCode = stockCode;
    }
    
    @Override
    public String toString() {
        return "fr.ensimag.projetjava.Stock[ id=" + name + " ]";
    }

    @Override
    public double getPrice(java.util.Calendar date) {
        return 121.25;
    }
    
}
