/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author josib
 */
@Entity
public class ParamStock extends Param {
    @ManyToOne(cascade = CascadeType.PERSIST)
    protected Stock val;
    protected String name;
    
    public ParamStock() {}
    
    public ParamStock(Stock p) {
        val = p;
    }
    
    public Stock getVal() {
        return val;
    }

    public void setVal(Stock val) {
        this.val = val;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        hash += (val != null ? val.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "fr.ensimag.projetjava.ParamStock[ value=" + val + " ]";
    }
    
}
