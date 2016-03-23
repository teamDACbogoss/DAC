/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author malacarc
 */
@Entity
public class Strategy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String strategyName;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ParamAssetInteger> assets;
    
    public Strategy() {}
    
    public Strategy(String strategyName, List<ParamAssetInteger> assets) {
        this.strategyName = strategyName;
        this.assets = assets;
    }
    
    public String getName() {
        return strategyName;
    }

    public void setName(String strategyName) {
        this.strategyName = strategyName;
    }

    public  List<ParamAssetInteger> getAssets() {
        return assets;
    }

    public void setAssets(List<ParamAssetInteger> assets) {
        this.assets = assets;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Strategy)) {
            return false;
        }
        Strategy other = (Strategy) object;
        return !((this.strategyName == null && other.strategyName != null) || (this.strategyName != null && !this.strategyName.equals(other.strategyName)));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.strategyName);
        return hash;
    }

    @Override
    public String toString() {
        return "fr.ensimag.projetjava.Strategy[ name=" + strategyName + " ]";
    }
    
    public double getPrice(java.util.Calendar date) {
        double result = 0.0;
        for(ParamAssetInteger assetInt : assets) {
            result += assetInt.asset.getPrice(date) * assetInt.quantity;
        }
        return result;
    }
    
}
