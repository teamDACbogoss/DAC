/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author josib
 */
@Entity
public class ParamStock extends Param {
    Stock value;
    
    public ParamStock() {}
    
    public ParamStock(Stock p) {
        value = p;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        hash += (value != null ? value.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "fr.ensimag.projetjava.ParamStock[ value=" + value + " ]";
    }
    
}
