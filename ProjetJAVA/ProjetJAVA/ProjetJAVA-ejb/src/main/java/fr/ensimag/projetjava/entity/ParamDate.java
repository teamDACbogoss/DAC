/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;

import java.util.Objects;
import javax.persistence.Entity;
//import javax.persistence.Temporal;

/**
 *
 * @author josib
 */
@Entity
public class ParamDate extends Param {
    //@Temporal(javax.persistence.TemporalType.DATE)
    protected long val;
    
    public ParamDate() {}
    
    public ParamDate(java.util.Calendar p, 
                     String name) {
        val = p.getTimeInMillis();
        this.name = name;
    }

    public java.util.Calendar getVal() {
        java.util.Calendar returnVal = java.util.Calendar.getInstance();
        returnVal.setTimeInMillis(this.val);
        return returnVal;
    }

    public void setVal(java.util.Calendar val) {
        this.val = val.getTimeInMillis();
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += val;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParamDate other = (ParamDate) obj;
        return Objects.equals(this.val, other.val);
    }

    @Override
    public String toString() {
        return "fr.ensimag.projetjava.ParamDate[ value=" + val + " ]";
    }
    
}
