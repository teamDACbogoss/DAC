/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author josib
 */
@Entity
public class ParamDate extends Param {
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date val;
    
    public ParamDate() {}
    
    public ParamDate(Date p) {
        val = p;
    }

    public Date getVal() {
        return val;
    }

    public void setVal(Date val) {
        this.val = val;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (val != null ? val.hashCode() : 0);
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
