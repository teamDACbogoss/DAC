/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author josib
 */
@Entity
public class Put extends Option {
    
    public Put() {
        super();
    }
    
    public Put(String name, Stock underlying, double strike, Date maturity) {
        this.underlying = new ParamStock(underlying);
        this.strike = new ParamDouble(strike);
        this.maturity = new ParamDate(maturity);
        this.name = "Put option on " + underlying.getName();
    }
    
    @Override
    public String toString() {
        return "fr.ensimag.projetjava.Put[ id=" + id + " ]";
    }

    @Override
    public double getPrice(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
