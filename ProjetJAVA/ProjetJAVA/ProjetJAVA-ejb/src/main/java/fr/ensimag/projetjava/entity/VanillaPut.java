/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author josib
 */
@Entity
public class VanillaPut extends FinancialOption {
    
    public VanillaPut() {}
 
    public VanillaPut(String name, Stock underlying, double strike, Date maturity) {
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
