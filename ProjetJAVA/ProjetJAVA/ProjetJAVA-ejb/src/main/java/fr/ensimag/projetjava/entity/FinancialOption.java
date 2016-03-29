/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;

import java.util.Random;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author josib
 */
@Entity
public abstract class FinancialOption extends Asset {
    @ManyToOne(cascade = CascadeType.PERSIST)
    protected ParamDouble strike;
    @ManyToOne(cascade = CascadeType.PERSIST)
    protected ParamStock underlying;
    @ManyToOne(cascade = CascadeType.PERSIST)
    protected ParamDate maturity;
    
    public FinancialOption() {};
    
    public FinancialOption(String maturityDate,
                           java.util.Calendar emissionDate)
    {
        super(emissionDate);
        this.maturityDate = maturityDate;
    }
}