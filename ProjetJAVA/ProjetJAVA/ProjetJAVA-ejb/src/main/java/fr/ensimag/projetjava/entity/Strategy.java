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
    
    private String assetNames;
    private String assetQuantity;
    private String assetPrice;
    private String assetPricePrevious;
    
    private String assetPriceProfit;
    private String assetMaturity;

    public String getAssetMaturity() {
        String st = "";
        for (ParamAssetInteger pai : this.assets)
        {
            st += pai.getAsset().getMaturityDate() + ";" + "\r\n";
        }     
        return st;    
    }

    public void setAssetMaturity(String assetMaturity) {
        this.assetMaturity = assetMaturity;
    }

    public String getAssetEmission() {
        String st = "";
        for (ParamAssetInteger pai : this.assets)
        {
            st += pai.getAsset().getEmissionDate() + ";" + "\r\n";
        }     
        return st;    
    }

    public void setAssetEmission(String assetEmission) {
        this.assetEmission = assetEmission;
    }
    private String assetEmission;

    public String getAssetPriceProfit() {
        
        String st = "";
        double temp = 0;
        for (ParamAssetInteger pai : this.assets)
        {
            temp += pai.getAsset().getPrice(null) - pai.getAsset().getPricePrevious(null);
        }
        st = Double.toString(temp);
        this.assetPrice = st.substring(0, Math.min(Double.toString(temp).length(), 5));        
        return st;
    }

    public void setAssetPriceProfit(String assetPriceProfit) {
        
        this.assetPriceProfit = assetPriceProfit;
    }
        


    public String getAssetQuantity() {
        String st = "";
        for (ParamAssetInteger pai : this.assets)
        {
            st = st + pai.getQuantity() + ";" + "\r\n";
        }
        this.assetQuantity = st;
        return st;
    }

    public void setAssetQuantity(String assetQuantity) {
        this.assetQuantity = assetQuantity;
    }

    public String getAssetPrice() {
        String st = "";
        for (ParamAssetInteger pai : this.assets)
        {
            st = st + pai.getAsset().getPrice(null)+ ";" + "\r\n";
        }
        this.assetPrice = st;
        return st;
    }

    public void setAssetPrice(String assetPrice) {
        this.assetPrice = assetPrice;
    }

    public String getAssetPricePrevious() {
        String st = "";
        for (ParamAssetInteger pai : this.assets)
        {
            st = st + pai.getAsset().getPricePrevious(null)+ ";" + "\r\n";
        }
        this.assetPricePrevious = st;
        return st;
    }

    public void setAssetPricePrevious(String assetPricePrevious) {
        this.assetPricePrevious = assetPricePrevious;
    }

    

    public String getAssetNames() {
        String st = "";
        for (ParamAssetInteger pai : this.assets)
        {
            st = st + pai.getAsset().getName() + ";" + "\r\n";
        }
        this.assetNames = st;
        return st;
    }

    public void setAssetNames(String assetNames) {
        this.assetNames = assetNames;
    }
    
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
