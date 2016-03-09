/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava;

import javax.persistence.Entity;

/**
 *
 * @author josib
 */
@Entity
public class ParamDouble extends Param {
    double val;
    
    public ParamDouble(){}
    
    public ParamDouble(double p) {
        val = p;
    }
    
    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += val;
        return hash;
    }

    @Override
    public String toString() {
        return "fr.ensimag.projetjava.ParamDouble[ value=" + val + " ]";
    }
}
