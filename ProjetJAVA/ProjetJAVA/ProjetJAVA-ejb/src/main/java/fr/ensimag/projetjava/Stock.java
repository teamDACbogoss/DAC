/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava;

import java.util.Date;
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
        return "fr.ensimag.projetjava.Stock[ id=" + id + " ]";
    }

    @Override
    public double getPrice(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
