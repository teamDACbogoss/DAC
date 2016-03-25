/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author randont
 */
@Entity
public class ParamAssetInteger extends Param implements Serializable {
    @ManyToOne
    protected Asset asset;
    protected int quantity;
    
    public ParamAssetInteger() {
        
    }
    
    public ParamAssetInteger(Asset asset){
        this.asset = asset;
        this.quantity = 0;
    }
    
    public ParamAssetInteger(Asset asset, int quantity) {
        this.asset = asset;
        this.quantity = quantity;
    }
    
    public Asset getAsset() 
    {
        return this.asset;
    }
    
    public void setAsset(Asset asset)
    {
        this.asset = asset;
    }
    
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int val) {
        this.quantity = val;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParamAssetInteger)) {
            return false;
        }
        ParamAssetInteger other = (ParamAssetInteger) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.ensimag.projetjava.entity.ParamStockInteger[ id=" + id + " ]";
    }
    
}
