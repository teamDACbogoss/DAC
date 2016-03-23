/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;

import javax.persistence.Entity;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.FastMath;

/**
 *
 * @author josib
 */
@Entity
public class VanillaCall extends FinancialOption {
    private static final long serialVersionUID = 1L;
    
    public VanillaCall(){}
    
    public VanillaCall(String name, Stock underlying, double strike, java.util.Calendar maturity) {
        this.underlying = new ParamStock(underlying, "underlying");
        this.strike = new ParamDouble(strike, "strike");
        this.maturity = new ParamDate(maturity, "maturity");
        this.name = "Call option on " + underlying.getName();
    }
    
    @Override
    public String toString() {
        return "fr.ensimag.projetjava.Call[ id=" + id + " ]";
    }

    @Override
    public double getPrice(java.util.Calendar date) {
        NormalDistribution dis = new NormalDistribution(0,1);
        double S = 100;//this.underlying.val.getPrice(date);
        double K = 100;//this.strike.val;
        double r = 0.02;
        double T = 1;
        double vol = 0.02;
        
        double volSqrt = vol * FastMath.sqrt(T);
        double d1 = (FastMath.log(S / K) + (r + vol * vol / 2) * T)/volSqrt;
        double d2 = d1 - volSqrt;
        double prix = dis.cumulativeProbability(d1) * S - dis.cumulativeProbability(d2) * K * FastMath.exp(-r * T);
        return prix;
    }
}
