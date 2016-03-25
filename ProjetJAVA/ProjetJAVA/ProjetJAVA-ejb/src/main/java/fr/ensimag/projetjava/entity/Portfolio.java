/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author malacarc
 */
@Entity
public class Portfolio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private List<Strategy> strategies;
    
    public Portfolio() {
        this.strategies = new ArrayList<>();
    }
    
    public Portfolio(List<Strategy> strategies)
    {
        this.strategies = strategies;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<Strategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public String toString() {
        return "fr.ensimag.projetjava.Portfolio[ id=" + id + " ]";
    }
    
    public double getValue(java.util.Calendar date) {
        double result = 0.0;
        for (Strategy strategy : this.strategies)
            result += strategy.getPrice(date);
        return result;
    }    
}
