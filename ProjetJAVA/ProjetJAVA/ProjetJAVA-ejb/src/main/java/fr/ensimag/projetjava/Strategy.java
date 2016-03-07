/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author malacarc
 */
@Entity
public class Strategy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String strategyName;
    private Map<Asset, Integer> assets;
    
    public Strategy() {}
    
    public Strategy(String strategyName, Map<Asset, Integer> assets) {
        this.strategyName = strategyName;
        this.assets = assets;
    }
    
    public String getName() {
        return strategyName;
    }

    public void setName(String strategyName) {
        this.strategyName = strategyName;
    }

    public Map<Asset, Integer> getAssets() {
        return assets;
    }

    public void setAssets(Map<Asset, Integer> assets) {
        this.assets = assets;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Strategy)) {
            return false;
        }
        Strategy other = (Strategy) object;
        if ((this.strategyName == null && other.strategyName != null) || (this.strategyName != null && !this.strategyName.equals(other.strategyName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.ensimag.projetjava.Strategy[ name=" + strategyName + " ]";
    }
    
    public double getPrice(Date date) {
        double result = 0.0;
        for (Entry<Asset, Integer> asset : this.assets.entrySet()) {
            result += asset.getValue() * asset.getKey().getPrice(date);
        }
        return result;
    }
    
}
