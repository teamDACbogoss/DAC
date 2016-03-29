/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;
import java.util.Calendar;
import javax.persistence.Entity;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.FastMath;

/**
 *
 * @author josib
 */
@Entity
public class VanillaPut extends FinancialOption {
    
    public VanillaPut() {}
 
    public VanillaPut(String maturityDate,
                      java.util.Calendar emissionDateCal){
        super(maturityDate, emissionDateCal);
    }
    
    public VanillaPut(String name, 
                      Stock underlying, 
                      double strike, 
                      java.util.Calendar maturity,
                      java.util.Calendar emissionDateCal,
                      String maturityStr) {
        super(maturityStr, emissionDateCal);
        this.underlying = new ParamStock(underlying, "underlying");
        this.strike = new ParamDouble(strike, "strike");
        this.maturity = new ParamDate(maturity, "maturity");
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "fr.ensimag.projetjava.Put[ id=" + name + " ]";
    }

    @Override
    public double getPrice(java.util.Calendar date) {
        NormalDistribution dis = new NormalDistribution(0,1);
        double S = this.underlying.val.getPrice(date);
        double K = this.strike.val;
        double r = 0.02;
        double T = (this.maturity.val - date.getTimeInMillis()) / (1000 * 60 * 60 * 24 * 365);
        if (T < 0) {
            return 0;
        }
        double vol = 0.2;
        double volSqrt = vol * FastMath.sqrt(T);
        double d1 = (FastMath.log(S / K) + (r + vol * vol / 2) * T) / volSqrt;
        double d2 = d1 - volSqrt;
        double prix = -dis.cumulativeProbability(-d1) * S + dis.cumulativeProbability(-d2) * K * FastMath.exp(-r * T);
        return prix;
    }

    @Override
    public double getPricePrevious(Calendar date) {
        NormalDistribution dis = new NormalDistribution(0,1);
        double S = this.underlying.val.getPrice(date);
        double K = this.strike.val;
        double r = 0.02;
        double T = (this.maturity.val - date.getTimeInMillis()) / (1000 * 60 * 60 * 24 * 365) - 1/365;
        if (T < 0) {
            return 0;
        }
        double vol = 0.2;
        double volSqrt = vol * FastMath.sqrt(T);
        double d1 = (FastMath.log(S / K) + (r + vol * vol / 2) * T)/volSqrt;
        double d2 = d1 - volSqrt;
        double prix = -dis.cumulativeProbability(-d1) * S + dis.cumulativeProbability(-d2) * K * FastMath.exp(-r * T);
        return prix;
    }
}
