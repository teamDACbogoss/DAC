/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;

import javax.persistence.Entity;
import java.util.Random;

/**
 *
 * @author josib
 */
@Entity
public class Stock extends Asset {
    String stockCode;
    String stockPrice;
    String stockPricePrevious;
    boolean bool;

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }
    
    public Stock() {
        super();
        Random r;
        r = new Random();
        Double st = 100*r.nextDouble();
        this.stockPrice = Double.toString(st).substring(0, Math.min(Double.toString(st).length(), 5));
        Double st1 = 100*r.nextDouble();
        this.stockPricePrevious = Double.toString(st1).substring(0, Math.min(Double.toString(st1).length(), 5));       
        this.bool = (Double.parseDouble(this.stockPrice) > Double.parseDouble(this.stockPricePrevious));  
    }
    
    public Stock(String name, String stockCode) {
        this.name = name;
        this.stockCode = stockCode;
        Random r;
        r = new Random();
        Double st = 100*r.nextDouble();
        this.stockPrice = Double.toString(st).substring(0, Math.min(Double.toString(st).length(), 5));
        Double st1 = 100*r.nextDouble();
        this.stockPricePrevious = Double.toString(st1).substring(0, Math.min(Double.toString(st1).length(), 5));
        this.bool = (Double.parseDouble(this.stockPrice) > Double.parseDouble(this.stockPricePrevious));    
    }
    
    public String getStockPrice()
    {
        return this.stockPrice;
    }
    
    public void setStockPrice(String stockPrice)
    {
        this.stockPrice = stockPrice;
    }
    
    public String getStockPricePrevious()
    {
        return this.stockPricePrevious;
    }
    
    public void setStockPricePrevious(String stockPricePrevious)
    {
        this.stockPricePrevious = stockPricePrevious;
    }
    
    @Override
    public String toString() {
        return "fr.ensimag.projetjava.Stock[ id=" + name + " ]";
    }

    @Override
    public double getPrice(java.util.Calendar date) {
        return Double.parseDouble(stockPrice);
    }
    
    @Override
    public double getPricePrevious(java.util.Calendar date) {
        return Double.parseDouble(stockPricePrevious);
    }
    
}
